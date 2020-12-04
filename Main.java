import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
	public static void main(String[] args) {
		Collection<Person> persons = Arrays.asList(
				new Person("Jack", "Evans", 16, Sex.MAN, Education.SECONDARY),
				new Person("Connor", "Young", 23, Sex.MAN, Education.FURTHER),
				new Person("Emily", "Harris", 42, Sex.WOMEN, Education.HIGHER),
				new Person("Harry", "Wilson", 69, Sex.MAN, Education.HIGHER),
				new Person("George", "Davies", 35, Sex.MAN, Education.FURTHER),
				new Person("Samuel", "Adamson", 40, Sex.MAN, Education.HIGHER),
				new Person("John", "Brown", 44, Sex.MAN, Education.HIGHER)
		);

		Stream<Person> stream1 = persons.stream();
		long underage = stream1.filter(person -> person.getAge() < 18).count();
		System.out.println("Kоличество несовершеннолетних " + underage + " человек");

		Stream<Person> stream2 = persons.stream();
		List<String> military = stream2.filter(person -> person.getSex().equals(Sex.MAN))
				.filter(person -> person.getAge() >= 18 && person.getAge() <= 27)
				.map(person -> person.getFamily())
				.collect(Collectors.toList());
		System.out.println("Список фамилий призывников: " + military);

		Stream<Person> stream3 = persons.stream();
		List<Person> higherEducation = stream3.filter(person -> person.getEducation().equals(Education.HIGHER))
				.filter(person -> person.getSex().equals(Sex.MAN) && person.getAge() >= 18 && person.getAge() <= 65 || person.getSex().equals(Sex.WOMEN) && person.getAge() >= 18 && person.getAge() <= 60)
				.sorted(Comparator.comparing(person -> person.getFamily()))
				.collect(Collectors.toList());

		System.out.println("Список потенциально работоспособных людей с высшим образованием:");
		for (Person p: higherEducation)
			System.out.println(p.toString());
	}
}
