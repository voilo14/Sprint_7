package ru.praktikum_servies.qa_scooter.courier;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;

public class CourierGenerator {

    public Courier getCourierDefault() {
        return new Courier("ninja", "1234", "saske");
    }

    public Courier getCourierRandom() {
        return new Courier(randomAlphanumeric(5), randomAlphanumeric(4), randomAlphanumeric(5));
    }
}
