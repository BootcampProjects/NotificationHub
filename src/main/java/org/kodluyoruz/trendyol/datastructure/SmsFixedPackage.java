package org.kodluyoruz.trendyol.datastructure;

import org.kodluyoruz.trendyol.datastructure.abstraction.FixedPackage;

public class SmsFixedPackage extends FixedPackage {
    public SmsFixedPackage() {
        super.limit = 1000;
        super.packagePrice = 20;
        super.limitExcessExtraLimit = 1000;
        super.limitExcessPackagePrice = 20;
    }
}
