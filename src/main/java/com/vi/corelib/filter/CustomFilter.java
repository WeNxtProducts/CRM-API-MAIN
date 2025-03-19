package com.vi.corelib.filter;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeType;
import com.oracle.truffle.regex.tregex.util.json.Json;
import com.vi.corelib.utils.DateUtils;
import com.vi.corelib.utils.JsonHelper;
import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static com.fasterxml.jackson.databind.node.JsonNodeType.*;

public class CustomFilter {

    public  String getFilter(String modelName, JsonNode filters) {

        /* Get All Keys of Json Object  */
        /* Check if they are reserved words that start with @ */
        /* Chcck if there are  CSV then use In */
        /* Check if the Json Array has an ! then use ! in */
        /* Check if the word contains % then use Like */
        /* Check if any word contains ! then use Not Like z */
        /* Check if the Value of the Key is Boolean/Date/String/Number"*/
        /* Assign the comparative values accordingly */
        StringBuilder str = new StringBuilder(" ");
        List<String> columnList = getKeys(filters);
        for(String key:columnList) {
            str.append(getValue(filters,key))
                    .append(" and ");
        }
        return str.toString().substring(0,str.length()-5);
    }
    private List<String>  getKeys(JsonNode filters) {
        return JsonHelper.getKeys(filters);
    }
    private String getArrayValueAsCsvString(String[] jsonList,String key) {
        StringBuilder str = new StringBuilder("");

        for(String s : jsonList) {
            str.append("'").append(s).append("',");
        }

        return  getDbKey(key)+ (str.toString().contains("!") ? " NOT " :  " ")+
                "IN ("+
                str.toString().substring(0,str.toString().length()-1)+")";
    }
    private String getArrayValueAsCsvNumber(JsonNode jsonList,String key) {
        StringBuilder str = new StringBuilder("");

        for(JsonNode s : jsonList) {
            str.append(s.asLong()).append(",");
        }
        return  getDbKey(key)+ (str.toString().contains("!") ? "NOT " :  " ")+
                "IN ("+
                str.toString().substring(0,str.toString().length()-1)+")";
    }
    private String getDbKey(String key) {
        String converted = " ";
        String dubKey = key;
        if(key.contains("::")){
            key=key.substring(0,key.indexOf(":"));
        }
        for(int i=0;i<key.length();i++) {
            converted = converted + (key.substring(i,i+1).toUpperCase().equals(key.substring(i,i+1))
                    ? "_" : "")+ key.substring(i,i+1).toLowerCase();
        }
        if(dubKey.contains("::")){
            converted+="::::date";
        }
        System.out.println(converted);
        return converted;
    }
    private JsonNodeType getValueOfArray(JsonNode filters,String key) {
        JsonNodeType firstValue = filters.get(key).get(0).getNodeType();
        if(filters.size()>1) {
            JsonNodeType secondValue = filters.get(key).get(1).getNodeType();
            if(secondValue==(JsonNodeType) NUMBER) {
                firstValue = (JsonNodeType) NUMBER;
            }
        }
        return firstValue;
    }
    private String getValue(JsonNode filters, String key) {

        JsonNodeType dataType1 = filters.get(key).getNodeType();
        StringBuilder str = new StringBuilder("  ");
        String dataType2 = "string";
        switch(dataType1) {
            case ARRAY:
                JsonNodeType firstValue = getValueOfArray(filters,key);
                if(firstValue==(JsonNodeType) STRING) {
                    return getArrayValueAsCsvString(new String[]{filters.get(key).asText()},key)+")";
                } else if(firstValue==(JsonNodeType) NUMBER) {
                    return getArrayValueAsCsvNumber(filters.get(key),key)+")";
                }
            case BOOLEAN:
                return getDbKey(key)+" = "+filters.get(key).asBoolean();
            case STRING:
                String value = filters.get(key).asText();
                if(value.charAt(0) == '@') {
                    return  getTranslatedValues(value,key);
                } else if(value.contains("%")) {
                    return getDbKey(key)+" like '"+value+"'";
                } else if(value.charAt(0)=='!' || value.contains(",")) {
                    var newData = value.split(" , ");
                    return  getArrayValueAsCsvString(newData,key);
                } else if(value.contains("__")) {
                    return  getRangeValues(value,key);
                } else {
                    return getDbKey(key)+" = '"+value+"'";
                }
            case OBJECT:
                if(filters.get(key).getNodeType()==STRING){
                    return getDbKey(key) + " ='"+filters.get(key).asText()+"'";
                }else if (filters.get(key).getNodeType()==BOOLEAN || filters.get(key).getNodeType()==NUMBER){
                    return getDbKey(key) + " ="+filters.get(key).asText()+"";
                }
            case NUMBER:
                return getDbKey(key) + " ="+filters.get(key).asText()+"";
        }
        return "";
    }
    private String getRangeValues(String str, String key) {
        String firstValue = str.substring(0,str.indexOf("__"));
        String secondValue = str.substring(str.indexOf("__")+2);

        return getDbKey(key)+" >="+(StringUtils.isNumeric(firstValue) ? firstValue.toString() +" and "+
                getDbKey(key)+" <="+secondValue.toString() :
                ( "'"+firstValue.toString() +"' and "+
                        getDbKey(key)+" <='"+secondValue.toString()+"'"));
    }
    private String getTranslatedValues(String filters,String key) {
        Date dt = new Date();
        String value;
        switch(filters.toUpperCase()) {
            case "@TODAY":
                String today = new SimpleDateFormat("yyyy-MM-dd").format(dt);
                return getDbKey(key)+" = '"+today+"'";
            case "@YESTERDAY":
                String dt2 = new SimpleDateFormat("yyyy-MM-dd").format(dt.getDate() - 1);
                return getDbKey(key)+" = '"+dt2+"'";
            case "@MONTH":
                return getDbKey(key)+" >= '"+ DateUtils.formatDate(DateUtils.getFirstDateOfMonth(new Date()))+ "' and "+
                        getDbKey(key)+" <= '"+  DateUtils.formatDate(DateUtils.getLastDateOfMonth(new Date()))+" ' ";
            case "@WEEK":
                return getDbKey(key)+" >= '"+ DateUtils.formatDate(DateUtils.getFirstDayOfWeek(new Date()))+ "' and "+
                        getDbKey(key)+" <= '"+  DateUtils.formatDate(DateUtils.getLastDayOfWeek(new Date()))+" ' ";

        }
        return " ";
    }
}
