package com.example.Spring.example;

//import com.sun.tools.javac.util.Convert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringApplication.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

@SpringBootApplication
@RestController
@Configuration


//@EnableConfigurationProperties({AppPr})


public class SpringExampleApplication {
	private static Object RequestBody;
	public static void main(String[] args) {
		SpringApplication.run(SpringExampleApplication.class, args);
	}

	// Получение данных из файла application.properties
	@Autowired
	private Environment env;


	//public static String welcomeMsg;

	@GetMapping("/hello")
	public static String hello(@RequestParam(value = "gf", defaultValue = "test") String name) {

		//String keyValue = env.getProperty(app.);
		System.out.println("${server.port}");
		//ResponseBody.class.toString() = "fgdf";
		return "Hello";
	}
	//Вывод статичного ответа
	@GetMapping("/test")
	public String test() throws InterruptedException {
		Thread.sleep(1);
		return "hello world";
	}

	//Вывод статичного ответа из параметров с задержкой из параметров
	@GetMapping("/properties")
	public String testP() throws InterruptedException {
		Thread.sleep(Long.parseLong(env.getProperty("timeSleep")));
		String responce = env.getProperty("test");
		return responce;
	}

	@GetMapping("/hello/man*")
	public static String helloman(@RequestParam String name) {
		System.out.println("test");
		String responce = name;
		return responce;
	}

	//Вывод статичного ответа xml, требует параметра с именем Name, отдает ответ в формате xml с типом xml
	@GetMapping(value="/hello/xml",produces = MediaType.TEXT_XML_VALUE)
	public static String helloxmlS(@RequestParam String name) {
		System.out.println(name);
		String responce = "<note>\n" +
				"<to>Вася</to>\n" +
				"<from>Света</from>\n" +
				"<heading>Напоминание</heading>\n" +
				"<body>Позвони мне завтра!</body>\n" +
				"</note>";
		return responce;
	}

	//Вывод ответа xml с динамическим параметром, требует параметра с именем Name, отдает ответ в формате xml с типом xml
	@GetMapping(value="/hello/xml/*",produces = MediaType.TEXT_XML_VALUE)
	public static String helloxmlD(@RequestParam String name) {
		System.out.println(name);
		String responce = "<note>\n" +
				"<to>Вася</to>\n" +
				"<from>"+name+"</from>\n" +
				"<heading>Напоминание</heading>\n" +
				"<body>Позвони мне завтра!</body>\n" +
				"</note>";
		return responce;
	}

	//Вывод ответа json с динамическим параметром, требует параметра с именем Name, отдает ответ в формате json с типом json
	@GetMapping(value="/hello/json*",produces = MediaType.APPLICATION_JSON_VALUE)
	public static String hellojson(@RequestParam String name) {
		System.out.println(name);
		String responce = "{\n" +
				"    \"glossary\": {\n" +
				"        \"title\": \"example glossary\",\n" +
				"\t\t\"GlossDiv\": {\n" +
				"            \"title\": \"S\",\n" +
				"\t\t\t\"GlossList\": {\n" +
				"                \"GlossEntry\": "+ name +"\n" +
				"                    \"ID\": \"SGML\",\n" +
				"\t\t\t\t\t\"SortAs\": \"SGML\",\n" +
				"\t\t\t\t\t\"GlossTerm\": \"Standard Generalized Markup Language\",\n" +
				"\t\t\t\t\t\"Acronym\": \"SGML\",\n" +
				"\t\t\t\t\t\"Abbrev\": \"ISO 8879:1986\",\n" +
				"\t\t\t\t\t\"GlossDef\": {\n" +
				"                        \"para\": \"A meta-markup language" + name +", used to create markup languages such as DocBook.\",\n" +
				"\t\t\t\t\t\t\"GlossSeeAlso\": [\"GML\", \"XML\"]\n" +
				"                    },\n" +
				"\t\t\t\t\t\"GlossSee\": \"markup\"\n" +
				"                }\n" +
				"            }\n" +
				"        }\n" +
				"    }\n" +
				"}";
		return responce;
	}

	//Вывод ответа json с динамическим параметром, требует параметра с именем Name, отдает ответ в формате json с типом json + задержка
	//тип post, требует тело запроса
	@Async
	@PostMapping(value = "/hello/man2",produces = MediaType.APPLICATION_XML_VALUE)
	public String hellomanPost(@RequestParam String name, @RequestBody String bod) throws InterruptedException {
		//String Body = (String) RequestBody;
		System.out.println(bod);

		Thread.sleep(Long.parseLong(env.getProperty("timeSleep")));
		return bod;
	}
}
