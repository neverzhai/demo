## list 方法排序
https://blog.csdn.net/lsgqjh/article/details/63686383

```java
list.stream().sorted(Comparator.comparing(Student::getAge).reversed());

```

## GroupingBy


## 排序

            LinkedHashMap<String, String> sortedMap = parameterMap.entrySet().stream().sorted(Map.Entry.comparingByKey())
                    .collect(Collectors.toMap(Map.Entry::getKey, e->StringUtils.join(e.getValue()),
                            (oldKey, newKey) -> oldKey, LinkedHashMap::new));
