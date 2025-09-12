// src/main/java/ch/tbz/m450/util/AddressComparator.java
package ch.tbz.m450.util;

import ch.tbz.m450.repository.Address;
import java.util.Comparator;

public class AddressComparator implements Comparator<Address> {
  @Override
  public int compare(Address a1, Address a2) {
    if (a1 == a2)
      return 0;
    if (a1 == null)
      return -1;
    if (a2 == null)
      return 1;
    int byLast = nullSafe(a1.getLastname()).compareToIgnoreCase(nullSafe(a2.getLastname()));
    if (byLast != 0)
      return byLast;
    int byFirst = nullSafe(a1.getFirstname()).compareToIgnoreCase(nullSafe(a2.getFirstname()));
    if (byFirst != 0)
      return byFirst;
    return Integer.compare(a1.getId(), a2.getId());
  }

  private static String nullSafe(String s) {
    return s == null ? "" : s;
  }
}
