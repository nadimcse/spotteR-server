package com.butterfly.spotter;

import com.google.common.base.CharMatcher;
import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import com.google.common.collect.Sets;

import java.util.List;
import java.util.Set;

/**
 * @author : Nadim
 * @since : 1/11/14
 */

public abstract class StringUtils {
    private StringUtils() {

    }

    public static boolean isBlank(final String str) {
        return Strings.isNullOrEmpty(str) || CharMatcher.WHITESPACE.matchesAllOf(str);
    }

    public static Set<String> extractStringIntoList(String listStr) {
        return Sets.newHashSet(
                Splitter.on(":")
                        .trimResults()
                        .omitEmptyStrings()
                        .split(listStr)
        );
    }

    public static String getPeerStrFromPeerSet(Set<String> peerSet) {
        String peerStr = "";
        for (String s : peerSet) {
            peerStr += ":" + s;
        }
        return peerStr;
    }
}
