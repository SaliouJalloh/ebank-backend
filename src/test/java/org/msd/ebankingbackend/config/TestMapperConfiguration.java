package org.msd.ebankingbackend.config;

import org.msd.ebankingbackend.infrastructure.mapper.*;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Import;

/**
 * Test configuration that imports all MapStruct mapper implementations.
 * This configuration can be imported in any test class that needs mapper beans.
 * 
 * Usage:
 * <pre>
 * {@code
 * @DataJpaTest
 * @Import(TestMapperConfiguration.class)
 * class MyTest {
 *     // Mappers will be available for injection
 * }
 * }
 * </pre>
 */
@TestConfiguration
@Import({
    IAccountPersistenceMapperImpl.class,
    IAddressPersistenceMapperImpl.class,
    IContactPersistenceMapperImpl.class,
    ICustomerPersistenceMapperImpl.class,
    IOperationPersistenceMapperImpl.class,
    ITransactionPersistenceMapperImpl.class
})
public class TestMapperConfiguration {
    // This class intentionally left empty - it only serves to import mapper implementations
}
