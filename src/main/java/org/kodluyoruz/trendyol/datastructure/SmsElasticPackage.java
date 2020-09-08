package org.kodluyoruz.trendyol.datastructure;

import org.kodluyoruz.trendyol.datastructure.abstraction.ElasticPackage;

public class SmsElasticPackage extends ElasticPackage {
    public SmsElasticPackage() {
        super.limit = 2000;
        super.packagePrice = 30;
        super.limitExcessUnitPrice = 0.10;
    }
}
