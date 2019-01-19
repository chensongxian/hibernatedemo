package com.csx.domain.anno;

import javax.persistence.*;

/**
 * @author csx
 * @Package com.csx.domain.anno
 * @Description: TODO
 * @date 2019/1/19 0019
 */
@Entity(name = "phone")
public class Phone {
    @Id
    private Long id;

    @Column(name = "phone_number")
    private String number;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "phone_type")
    private PhoneType type;

    public Phone() {
    }

    public Phone(Long id, String number, PhoneType type) {
        this.id = id;
        this.number = number;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public PhoneType getType() {
        return type;
    }

    public void setType(PhoneType type) {
        this.type = type;
    }
}
