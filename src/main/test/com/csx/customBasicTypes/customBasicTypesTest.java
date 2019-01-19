package com.csx.customBasicTypes;

import com.csx.domain.anno.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.Test;

import java.util.BitSet;

/**
 * @author csx
 * @Package com.csx.customBasicTypes
 * @Description: 自定义BasicType实现
 * @date 2019/1/19 0019
 */
public class customBasicTypesTest {
    private static SessionFactory sf;

    static {
        Configuration configuration = new Configuration().configure();
        configuration.registerTypeContributor((typeContributions, serviceRegistry) -> {
            typeContributions.contributeType(BitSetType.INSTANCE);
        });
        sf = configuration.buildSessionFactory();

        /*
        ServiceRegistry standardRegistry =
                new StandardServiceRegistryBuilder().build();

        MetadataSources sources = new MetadataSources( standardRegistry );

        MetadataBuilder metadataBuilder = sources.getMetadataBuilder();

        metadataBuilder.applyBasicType( BitSetType.INSTANCE );

        sf = configuration.buildSessionFactory(standardRegistry);
        */

    }

    @Test
    public void testBasicType() {
        BitSet bitSet = BitSet.valueOf( new long[] {1, 2, 3} );

        Session session = sf.openSession();
        Product product = new Product( );
        product.setId( 1 );
        product.setBitSet( bitSet );
        session.save(product);

//        doInHibernate( this::sessionFactory, session -> {
//            Product product = session.get( Product.class, 1 );
//            assertEquals(bitSet, product.getBitSet());
//        } );
    }
}
