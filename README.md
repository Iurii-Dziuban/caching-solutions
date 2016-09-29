# Caching-solutions
A project that shows caching solution approaches with code generation
[Lombok](https://projectlombok.org/) and [AutoValue](https://github.com/google/auto/blob/master/value/userguide/index.md)

#Structure
- `org.caching.autovalue` contains example with builder, equals, hashcode, getters, setters example without much flexibility
- `org.caching.lombok` contains examples with Lombok features. In IDE plugins for Lombok are needed.
Main features: getters, setters, equals, hashcode, builder, lazy getter, sneakyThrows, synchronized, logger code generation.
- `EhcacheTransactionDaoTest` test for direct usage of ehcache
- `SpringCacheTransactionDaoTest` test for spring simple cache usage
Tests show saving to cache / without cache and reading the values. With Cache look ups are quicker. Evict does nothing, cause small data.
Clearing cache shows that cache is not hit in this case.

# Building project
`mvn clean package`

# Pom.xml
Libraries:
- ehcache
- spring
- lombok
- autovalue
- log4j logging (possibility to configure) via slf4j