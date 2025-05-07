package com.vi.corelib.filter;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.vi.corelib.UserInfo;
import com.vi.corelib.utils.DateUtils;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import reactor.util.annotation.Nullable;

public abstract class Filter {
	public static String getDefault(String controller, @Nullable UserInfo userInfo, @Nullable HashMap<String, String> json) {
		return getDefault();
	}
	
	public static String getDefault() {
		return "search=deleted:false";
	}

	public static Matcher getMatcher(String search) {
		Pattern pattern = Pattern.compile("(\\w+?)(:|<|>|like|in)([^,]+),");
		Matcher matcher = pattern.matcher(search + ",");
		return matcher;
	}

	public static Predicate getInterpretedDate(Root root, CriteriaQuery query, CriteriaBuilder builder, FilterCriteria criteria) {
		Date dt = new Date();
		String value;
		switch (criteria.getValue().toString()) {
			case "@TODAY":
				String today = new SimpleDateFormat("YYYY-MM-DD").format(dt);
				builder.equal(root.<String>get(criteria.getKey()), today);
				break;
			case "@YESTERDAY":
				String dt2 = new SimpleDateFormat("YYYY-MM-DD").format(dt.getDate() - 1);
				builder.equal(root.<String>get(criteria.getKey()), dt2);
				break;
			case "@MONTH":
				return builder.and(
						builder.greaterThanOrEqualTo(root.<String>get(criteria.getKey()), DateUtils.formatDate(DateUtils.getFirstDateOfMonth(new Date()))),
						builder.lessThanOrEqualTo(root.<String>get(criteria.getKey()), DateUtils.formatDate(DateUtils.getLastDateOfMonth(new Date())))
				);

			case "@WEEK":
				return builder.and(
						builder.greaterThanOrEqualTo(root.<String>get(criteria.getKey()), DateUtils.formatDate(DateUtils.getFirstDayOfWeek(new Date()))),
						builder.lessThanOrEqualTo(root.<String>get(criteria.getKey()), DateUtils.formatDate(DateUtils.getLastDayOfWeek(new Date())))
				);
      /*case "@NEXTWEEK":
        String dt2 = new SimpleDateFormat("YYYY-MM-DD").format(dt.getDate()-1);
        builder.equal( root.<String>get(criteria.getKey()), dt2);
        break;
      case "@YEAR":
        String dt2 = new SimpleDateFormat("YYYY-MM-DD").format(dt.getDate()-1);
        builder.equal( root.<String>get(criteria.getKey()), dt2);
        break;
      case "@NEXTYEAR":
        String dt2 = new SimpleDateFormat("YYYY-MM-DD").format(dt.getDate()-1);
        builder.equal( root.<String>get(criteria.getKey()), dt2);
        break;
      case "@NEXTBUSINESSDAY":
        String dt2 = new SimpleDateFormat("YYYY-MM-DD").format(dt.getDate()-1);
        builder.equal( root.<String>get(criteria.getKey()), dt2);
        break;
      case "@PREVBUSINESSDAY":
        String dt2 = new SimpleDateFormat("YYYY-MM-DD").format(dt.getDate()-1);
        builder.equal( root.<String>get(criteria.getKey()), dt2);
        break;
      case "@LASTYEEARTHISTIME":
        String dt2 = new SimpleDateFormat("YYYY-MM-DD").format(dt.getDate()-1);
        builder.equal( root.<String>get(criteria.getKey()), dt2);
        break;*/
		}
		return null;
	}

	public static Predicate getFilter(Root root, CriteriaQuery query, CriteriaBuilder builder, FilterCriteria criteria) {
		System.out.println("---------------------------Leadcreation"+ criteria);
		if (criteria.getOperation().equalsIgnoreCase(">=")) {
			return builder.greaterThanOrEqualTo(
					root.<String>get(criteria.getKey()), criteria.getValue().toString());
		} else if (criteria.getOperation().equalsIgnoreCase(">")) {
			return builder.greaterThan(
					root.<String>get(criteria.getKey()), criteria.getValue().toString());
		} else if (criteria.getOperation().equalsIgnoreCase("<=")) {
			return builder.lessThanOrEqualTo(
					root.<String>get(criteria.getKey()), criteria.getValue().toString());
		} else if (criteria.getOperation().equalsIgnoreCase("<")) {
			return builder.lessThanOrEqualTo(
					root.<String>get(criteria.getKey()), criteria.getValue().toString());
		} else if (criteria.getOperation().equalsIgnoreCase("IN")) {
			if (root.get(criteria.getKey()).getJavaType() == String.class) {
				List<String> parentList = List.of(criteria.getValue().toString().split(","));
				CriteriaBuilder.In<String> inClause = builder.in(root.<String>get(criteria.getKey()));
				//parentList.forEach(in::value);
				for (String str : parentList) {
					inClause.value(str);
				}
				return inClause;
			} else {
				List<String> parentList = List.of(criteria.getValue().toString().split(","));
				CriteriaBuilder.In<Long> inClause = builder.in(root.<String>get(criteria.getKey()));
				//parentList.forEach(in::value);
				for (String str : parentList) {
					inClause.value(Long.valueOf(str));
				}
				return inClause;
			}
			//return builder.in(in);
		} else if (criteria.getOperation().equalsIgnoreCase("NOT IN")) {
			List<String> parentList = List.of(criteria.getValue().toString().split(","));
			CriteriaBuilder.In<String> in = builder.in(root.<String>get(criteria.getKey()));
			parentList.forEach(in::value);
			return builder.not(builder.in(in));
		} else if (criteria.getOperation().substring(1, 1).equalsIgnoreCase("@")) {
			return getInterpretedDate(root, query, builder, criteria);
		} else if (criteria.getOperation().equalsIgnoreCase("like")) {
			if (root.get(criteria.getKey()).getJavaType() == String.class) {
				return builder.like(
						root.<String>get(criteria.getKey()), "%" + criteria.getValue() + "%");
			}
		} else if (criteria.getOperation().equalsIgnoreCase(":")) {
			if (root.get(criteria.getKey()).getJavaType() == String.class) {
				if (criteria.getValue().toString().contains("%")) {
					return builder.like(
							root.<String>get(criteria.getKey()), "%" + criteria.getValue() + "%");
				} else {
					return builder.equal(
							root.<String>get(criteria.getKey()), criteria.getValue());
				}
			} else if (root.get(criteria.getKey()).getJavaType() == Boolean.class) {
				Predicate p = builder.conjunction();
				if (criteria.getValue().toString().equals("true")) {
					return builder.isTrue(root.<String>get(criteria.getKey()));
					//return builder.and(p, root.<String>get(criteria.getKey()));
				} else {
					return builder.isFalse(root.<String>get(criteria.getKey()));
				}
			} else {
				return builder.equal(root.get(criteria.getKey()), criteria.getValue());
			}
		} else if (criteria.getOperation().equalsIgnoreCase("__") ||
			    criteria.getOperation().equalsIgnoreCase("RANGE") ||
			    criteria.getOperation().equalsIgnoreCase("BETWEEN")) {

			    System.out.println("---- Inside BETWEEN operation ----");

			 // Parse the String dates into LocalDate
			    String[] dateRange = criteria.getValue().toString().split("__");

			    LocalDate fromDate = LocalDate.parse(dateRange[0]);
			    LocalDate toDate = LocalDate.parse(dateRange[1]);

			    // Convert to LocalDateTime to handle time parts
			    LocalDateTime fromDateTime = fromDate.atStartOfDay(); // 2025-03-29 00:00:00
			    LocalDateTime toDateTime = toDate.plusDays(1).atStartOfDay().minusNanos(1); // 2025-04-28 23:59:59.999999999

			    // Convert LocalDateTime to java.util.Date
			    Date fromDateConverted = Date.from(fromDateTime.atZone(ZoneId.systemDefault()).toInstant());
			    Date toDateConverted = Date.from(toDateTime.atZone(ZoneId.systemDefault()).toInstant());

//			    System.out.println("############################### From: " + fromDateConverted);
//			    System.out.println("############################### To: " + toDateConverted);

			    return builder.and(
			        builder.greaterThanOrEqualTo(root.get(criteria.getKey()), fromDateConverted),
			        builder.lessThanOrEqualTo(root.get(criteria.getKey()), toDateConverted)
			    );

			}
			return null;


	}
}
