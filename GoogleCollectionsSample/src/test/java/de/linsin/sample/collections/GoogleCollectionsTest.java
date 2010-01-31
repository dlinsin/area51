/*
 * Copyright 2009 David Linsin
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package de.linsin.sample.collections;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Maps.newHashMap;
import static com.google.common.collect.Ordering.from;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

import de.linsin.sample.domain.EmailAddress;
import de.linsin.sample.domain.Person;
import org.junit.Test;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;

public class GoogleCollectionsTest {

	@Test
	public void initialize3() {
		Map<String, String> map = newHashMap();
		map.put("key1", "value1");
		map.put("key2", "value2");

		Map<String, String> map1 = ImmutableMap.of("key1", "value1", "key2", "value2");

		assertEquals(map, map1);
	}

	@Test
	public void initialize2() {
		List<String> list = newArrayList();
		list.add("a");
		list.add("b");

		List<String> list1 = ImmutableList.of("a", "b");

		assertEquals(list, list1);
	}

	@Test
	public void multimap_traditional() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("key1", "value1");
		map.put("key1", "value1.1"); // with Map key1=value1.1
		map.put("key2", "value2");
		map.put("key2", "value2.1");

		assertEquals(map.get("key1"), "value1.1");
		assertEquals(map.get("key2"), "value2.1");
	}

	@Test
	public void multimap() {
		Multimap<String, String> map = HashMultimap.create();
		map.put("key1", "value1");
		map.put("key1", "value1.1"); // with Map key1=value1.1
		map.put("key2", "value2");
		map.put("key2", "value2.1");

		assertTrue(map.get("key1").size() == 2);

		Map<String, Collection<String>> expected = new HashMap<String, Collection<String>>();
		expected.put("key1", new LinkedHashSet<String>(Arrays.asList("value1.1", "value1")));
		expected.put("key2", new LinkedHashSet<String>(Arrays.asList("value2.1", "value2")));

		assertEquals(expected, map.asMap());
	}

	@Test
	public void bimap_traditional() {
		Map<String, String> map = newHashMap();
		map.put("key1", "value1");
		map.put("key2", "value2");

		assertEquals("value2", map.put("key2", "value3"));
		assertEquals("value3", map.get("key2"));

		map.put("key3", "value3");
		assertEquals("value3", map.get("key3"));
	}

	@Test(expected = IllegalArgumentException.class)
	public void bimap() {
		Map<String, String> map = HashBiMap.create();
		map.put("key1", "value1");
		map.put("key2", "value2");

		assertEquals("value2", map.put("key2", "value3"));
		assertEquals("value3", map.get("key2"));

		map.put("key3", "value3"); // throws Exception
	}

	@Test
	public void inverse_bimap() {
		BiMap<String, Integer> map = HashBiMap.create();
		map.put("key1", Integer.valueOf(1));
		map.put("key2", Integer.valueOf(2));

		Map<Integer, String> inverse = map.inverse();
		assertEquals("key1", inverse.get(Integer.valueOf(1)));
		assertEquals("key2", inverse.get(Integer.valueOf(2)));
	}

	@Test
	public void transform() {
		List<Person> allEmployees = newArrayList(new Person("kleber"), new Person("seibert"), new Person("slomka"));
		allEmployees = Lists.transform(allEmployees, new Function<Person, Person>() {
			public Person apply(Person argPerson) {
				argPerson.setEmailAddress(new EmailAddress(argPerson.getName().concat("@zdf.de")));
				return argPerson;
			}
		});

		assertEquals(allEmployees.get(0).getEmailAddress().getEmail(), "kleber@zdf.de");
	}

	@Test
	public void find() {
		List<Person> allEmployees = newArrayList(new Person("kleber"), new Person("seibert"), new Person("slomka"));
		Person kleber = Iterables.find(allEmployees, new Predicate<Person>() {
			public boolean apply(Person argPerson) {
				return argPerson.getName().equals("kleber");
			}
		});
		assertEquals(allEmployees.get(0), kleber);
	}

	@Test
	public void order() {
		List<Person> allEmployees = newArrayList(new Person("seibert"), new Person("slomka"), new Person("kleber"));
		Comparator<Person> comp = new Comparator<Person>() {
			public int compare(Person person1, Person person2) {
				return person1.getName().compareTo(person2.getName());
			}

		};
		List<Person> sorted = from(comp).sortedCopy(allEmployees);
		assertEquals("kleber", sorted.get(0).getName());
		assertEquals("seibert", sorted.get(1).getName());
		assertEquals("slomka", sorted.get(2).getName());

		assertTrue(allEmployees.get(2) == sorted.get(0));
		assertFalse(allEmployees == sorted);
	}

	@Test
	public void difference() {
		Map<Long, Person> allEmployees = ImmutableMap.of(1L, new Person("seibert"), 2L, new Person("slomka"), 3L, new Person("kleber"));
		Map<Long, Person> allMaleEmployees = newHashMap(allEmployees);
		allMaleEmployees.remove(2L);

		Map<Long, Person> intersect = Maps.difference(allEmployees, allMaleEmployees).entriesInCommon();
		assertTrue(intersect.size() == 2);
		assertNull(intersect.get(2L));
	}

}
