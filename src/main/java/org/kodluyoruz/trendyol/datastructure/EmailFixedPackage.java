package org.kodluyoruz.trendyol.datastructure;

import org.kodluyoruz.trendyol.datastructure.abstraction.FixedPackage;

public class EmailFixedPackage extends FixedPackage {
    public EmailFixedPackage() {
        super.limit = 1000;
        super.packagePrice = 10;
        super.limitExcessExtraLimit = 1000;
        super.limitExcessPackagePrice = 10;
    }
}
