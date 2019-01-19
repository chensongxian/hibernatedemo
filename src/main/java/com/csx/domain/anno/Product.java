package com.csx.domain.anno;

import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.BitSet;

/**
 * @author csx
 * @Package com.csx.domain.anno
 * @Description: TODO
 * @date 2019/1/19 0019
 */
@Entity(name = "Product")
public class Product {
    @Id
    private Integer id;

    @Type(type = "bitset")
    private BitSet bitSet;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BitSet getBitSet() {
        return bitSet;
    }

    public void setBitSet(BitSet bitSet) {
        this.bitSet = bitSet;
    }
}
