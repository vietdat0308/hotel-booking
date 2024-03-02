package com.hrs.hotelbooking.util;

import java.io.StringWriter;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import lombok.NonNull;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PatternMatchUtils;

/**
 * This class helps to provide string parsing utility.
 *
 * @author datnguyenv1
 * @version 1.0
 * @since 2024/03/02
 */
@UtilityClass
@Slf4j
public class StringUtil {

  /**
   * This function is to add escape for all special character in sql query.
   *
   * @param inputString to be parse
   * @return string formatted with pattern
   */
  public static String replaceSpecialCharacter(@NonNull String inputString) {
    final String[] metaCharacters = {"\\", "^", "$", "{", "}", "[", "]", "(", ")", ".", "*", "+",
        "?", "|", "<", ">", "-", "&", "%", "_"};
    for (String metaCharacter : metaCharacters) {
      inputString = inputString.replace(metaCharacter, "\\" + metaCharacter);
    }
    return inputString;
  }

  /**
   * Process valid search key.
   *
   * @param searchKey searchKey
   * @return valid searchKey
   */
  public static String processValidSearchKey(String searchKey) {
    if (StringUtils.isEmpty(searchKey)) {
      return null;
    } else {
      String validSearchKey = StringUtil.replaceSpecialCharacter(searchKey);
      return validSearchKey.toLowerCase().trim();
    }
  }

}
