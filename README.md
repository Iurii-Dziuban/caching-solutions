# Caching-solutions
[![Open Source Love](https://badges.frapsoft.com/os/v2/open-source.svg?v=103)](https://github.com/ellerbrock/open-source-badge/)

[![Build Status](https://travis-ci.org/Iurii-Dziuban/caching-solutions.svg?branch=master)](https://travis-ci.org/Iurii-Dziuban/caching-solutions)
[![Coverage Status](https://coveralls.io/repos/github/Iurii-Dziuban/caching-solutions/badge.svg?branch=master)](https://coveralls.io/github/Iurii-Dziuban/caching-solutions?branch=master)
[![Dependency Status](https://www.versioneye.com/user/projects/5a11b2530fb24f2a3bd25760/badge.svg?style=flat-square)](https://www.versioneye.com/user/projects/5a11b2530fb24f2a3bd25760)
[![contributions welcome](https://img.shields.io/badge/contributions-welcome-brightgreen.svg?style=flat)](https://github.com/Iurii-Dziuban/caching-solutions/issues)

A project that shows caching solution approaches with code generation
[Lombok](https://projectlombok.org/) and [AutoValue](https://github.com/google/auto/blob/master/value/userguide/index.md)

## Table of contents:
 * [Static Analysis QA Checks](#checks)
 * [Project parts](#structure)
 * [Project build](#building-project)
 * [Test coverage](#coverage)
 * [Libraries](#pomxml)
 * [Ideas to try](#ideas)
 
# Checks

`Jacoco`/`cobertura` code coverage, `pmd`, `checkstyle`, `enforcer`, `findbugs`

# Structure
- `org.caching.autovalue` contains example with builder, equals, hashcode, getters, setters example without much flexibility
- `org.caching.lombok` contains examples with Lombok features. In IDE plugins for Lombok are needed.
Main features: getters, setters, equals, hashcode, builder, lazy getter, sneakyThrows, synchronized, logger code generation.
- `Ehcache2TransactionDaoTest` test for direct usage of ehcache version 2 with spring
- `JbossCacheTransactionDaoTest` test with Jboss cache usage
- `JCacheEhCache2TransactionDaoTest` jcache standard protocol with Ehcache 2 version as an implementation
Use getClass().getClassLoader().getResourceAsStream("filename") instead of full path to file (like: scr/main/resources/filename) which is not a case in jar file
- `JCacheEhCache3TransactionDaoTest` jcache standard protocol with Ehcache 3 version as an implementation with spring 
(no spring configuration for direct usage of ehcache 3 yet). 
- `JCacheHazelcastTransactionDaoTest` jcache standard protocol with Hazelcast as an implementation
- `SpringCacheTransactionDaoTest` test for spring simple cache usage
Tests show saving to cache / without cache and reading the values. With Cache look ups are quicker. Evict does nothing, cause small data.
Clearing cache shows that cache is not hit in this case.
- `GuavaCacheTest` test Guava abilities to cache
# Building project
`mvn clean package`

# Coverage
Excluded some auto generated classes from cobertura and coveralls coverage for `lombok` and `autovalue` cause they do not support it well

# Pom.xml
Libraries:
- hazelcast
- ehcache 2
- ehcache 3
- jboss cache
- jcache
- guava
- spring
- lombok
- autovalue
- log4j logging (possibility to configure) via slf4j

# Ideas