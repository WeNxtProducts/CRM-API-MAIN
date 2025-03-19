package com.vi.corelib.utils;

import org.passay.CharacterData;
import org.passay.*;
import java.util.Arrays;
import java.util.List;

public class RandomPasswordGenerator {
    public String generatePassword() {
        List<CharacterRule> rules = Arrays.asList(
                new CharacterRule(EnglishCharacterData.UpperCase, 1),
                new CharacterRule(EnglishCharacterData.LowerCase, 1),
                new CharacterRule(new CharacterData() {
                    @Override
                    public String getErrorCode() {
                        return "ERR_SPACE";
                    }

                    @Override
                    public String getCharacters() {
                        return "!@#$%^&*()_+";
                    }
                }, 1)
        );
        var generator = new PasswordGenerator();
        return generator.generatePassword(12, rules);
    }
    public Boolean passwordValidator(String pwd) {
        List<CharacterRule> rules = Arrays.asList(
                new CharacterRule(EnglishCharacterData.UpperCase, 1),
                new CharacterRule(EnglishCharacterData.LowerCase, 1),
                new CharacterRule(new CharacterData() {
                    @Override
                    public String getErrorCode() {
                        return "ERR_SPACE";
                    }

                    @Override
                    public String getCharacters() {
                        return "!@#$%^&*()_+";

                    }
                }, 1)
        );
        var validator = new PasswordValidator(rules);
        var data = new PasswordData(pwd);
        RuleResult result = validator.validate(data);
        return result.isValid();
    }
}
