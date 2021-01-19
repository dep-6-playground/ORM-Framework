package lk.ijse.dep.orm.util;

import org.junit.jupiter.api.Test;

import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author : Deshan Charuka <d.c.0729439631@gmail.com>
 * @since : 2021-01-20
 **/
class ORMUtilTest {

    @Test
    void init() throws Exception {
        ORMUtil.init(new Properties(), Customer.class,Item.class);
    }
}
