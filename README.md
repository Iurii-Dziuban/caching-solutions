# caching-solutions
Caching solution aproaches project with code generation Lombok([Lombok]:https://projectlombok.org/) and AutoValue ([AutoValue]: https://github.com/google/auto/blob/master/value/userguide/index.md)

#Structure
- `org.caching.autovalue` contains example with builder, equals, hashcode, getters, setters example
- `org.caching.lombok` contains examples with lombok features. In IDE plugins for lombok are needed.
Main features: getters, setters, equals, hashcode, builder, lazy getter, sneakyThrows, synchronized, logger code generation.
- `EhcacheTransactionDaoTest` test for direct usage of ehcache
- `SpringCacheTransactionDaoTest` test for spring simple cache usage