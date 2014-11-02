package org.brightify.torch.compile.util;

import java.util.Comparator;

/**
 * @author <a href="mailto:tadeas@brightify.org">Tadeas Kriz</a>
 */
public class VersionComparator implements Comparator<String> {

    @Override
    public int compare(String v1, String v2) {
        String[] version1Split = v1.split("\\.");
        String[] version2Split = v2.split("\\.");

        int count = version1Split.length > version2Split.length ? version1Split.length : version2Split.length;

        for(int i = 0; i < count; i++) {
            Integer version1Part = i < version1Split.length ? Integer.parseInt(version1Split[i]) : 0;
            Integer version2Part = i < version2Split.length ? Integer.parseInt(version2Split[i]) : 0;

            int compared = version1Part.compareTo(version2Part);
            if(compared != 0) {
                return compared;
            }
        }

        return 0;
    }

    public static int compareVersions(String v1, String v2) {
        VersionComparator versionComparator = new VersionComparator();

        return versionComparator.compare(v1, v2);
    }
}
