package com.example.multchat;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
// class to check if the host and port are valid
public class ChatLogic {
    // expression for IPv4 address
    private static final String IPV4_REGEX =
            "^(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\." +
            "(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\." +
            "(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\." +
            "(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$";
    private static final Pattern IPv4_PATTERN = Pattern.compile(IPV4_REGEX);
    //program to validate IPv4 address
    public static boolean isValidInet4Address(String ip)
    {
        if (ip == null) {
            return false;
        }
        Matcher matcher = IPv4_PATTERN.matcher(ip);
        return matcher.matches();
    }
    //program to validate IPv6 address
    public boolean isValidInet6Address(String ip)
    {
        String[] groups = ip.split(":");

        if (groups.length != 8) {
            return false;
        }
        try {
            return Arrays.stream(groups)
                    .filter(s -> s.length() > 1 && s.startsWith("0"))
                    .map(Integer::parseInt)
                    .filter(i -> (i >= 0 && i <= 65535))
                    .count() == 8;
        } catch (NumberFormatException e) { return false; }
    }
    //program to validate IPv4 or IPv6 address
    public boolean isValidInetAddress(String ip) {return isValidInet4Address(ip) || isValidInet6Address(ip);}
    //check is the port is valid
    public boolean isValidPort(String port)
    {
        try {
            int p = Integer.parseInt(port);
            return p > 1023 && p < 65536;
        } catch (NumberFormatException e) { return false; }
    }
}
