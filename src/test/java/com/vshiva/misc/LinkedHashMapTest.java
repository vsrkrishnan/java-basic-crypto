package com.vshiva.misc;

import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

import org.junit.platform.commons.util.StringUtils;

/**
 * @author sviswanathan
 * Jun 07, 2021
 */
public class LinkedHashMapTest
{
    public static void main(String[] args) throws UnknownHostException {
        /*Map<Container, String> map = new LinkedHashMap<>();
        map.put(new Container("John"), "1");
        map.put(new Container("Jos"), "2");
        map.put(new Container("Broad"), "3");
        map.put(new Container("Cook"), "4");
        map.put(new Container("Smith"), "5");
        map.forEach((k, v) -> System.out.println(v));*/


        var address = "";
        System.out.println(StringUtils.isNotBlank(address) ? address : "NA");

        System.out.println(resolveIpAddressForDomain(address, null));
        System.out.println(resolveIpAddressForDomain(address, "ipv4"));
        System.out.println(resolveIpAddressForDomain(address, "ipv6"));
    }

    public static String resolveIpAddressForDomain(String domainString, String ipType) {
        if (StringUtils.isBlank(domainString)) {
            return null;
        }

        if (StringUtils.isNotBlank(ipType)) {
            ipType = ipType.toUpperCase();
        }

        String address = domainString.trim();


        try {
            InetAddress[] iaList = InetAddress.getAllByName(address);
            for (var ia : iaList) {
                if (StringUtils.isNotBlank(ipType)) {
                    switch (ipType) {
                        case "IPV4":
                            if (ia instanceof InetAddress) {
                                return ia.getHostAddress();
                            }
                            break;
                        case "IPV6":
                            if (ia instanceof Inet6Address) {
                                return ia.getHostAddress();
                            }
                            break;
                        default:
                            throw new IllegalArgumentException(String.format("The IP Type [%s] provided is an invalid type. Supported types are [IPV4, IPV6]", ipType));
                    }
                } else {
                    return ia.getHostAddress();
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    public static class Container {
        private String name;
        public Container(String name) {
            this.name = name;
        }
        public String getName() {
            return name;
        }
        public int hashCode() {
            return 997;
        }
        public boolean equals(Object obj) {
            if (Objects.isNull(obj))
                return false;
            Container that = (Container) obj;
            return this.getName().length() == that.getName().length();
        }
    }
}
