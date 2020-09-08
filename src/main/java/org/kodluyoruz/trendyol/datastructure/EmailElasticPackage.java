package org.kodluyoruz.trendyol.datastructure;

import org.kodluyoruz.trendyol.datastructure.abstraction.ElasticPackage;

public class EmailElasticPackage extends ElasticPackage {
    public EmailElasticPackage() {
        super.limit = 2000;
        super.packagePrice = 7.5;
        super.limitExcessUnitPrice = 0.03;
    }
}
