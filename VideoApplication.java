package encoway.springframework.videoapplication;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

/**
 * this Class represents that main Application class
 *
 * @author Mohamad Alkasem
 * @version 1.0
 * @since November 2023
 */

@SpringBootApplication
@ComponentScan(basePackages = {"encoway.springframework.videoapplication.service.implementation", "encoway.springframework.videoapplication.controller"})
public class VideoApplication {
	/**
	 * <p>
	 * Configure ModelMapper class as a Spring Bean
	 * ModelMapper class will be injected in the controllers
	 * @return ModelMapper class
	 */
	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}
	public static void main(String[] args) {
		SpringApplication.run(VideoApplication.class, args);

		System.out.println("\n" +
				"██╗  ██╗███████╗██╗     ██╗      ██████╗     ██╗███╗   ██╗         ██╗ █████╗ ██╗   ██╗ █████╗ ███████╗██╗     ██╗██╗  ██╗\n" +
				"██║  ██║██╔════╝██║     ██║     ██╔═══██╗    ██║████╗  ██║         ██║██╔══██╗██║   ██║██╔══██╗██╔════╝██║     ██║╚██╗██╔╝\n" +
				"███████║█████╗  ██║     ██║     ██║   ██║    ██║██╔██╗ ██║         ██║███████║██║   ██║███████║█████╗  ██║     ██║ ╚███╔╝ \n" +
				"██╔══██║██╔══╝  ██║     ██║     ██║   ██║    ██║██║╚██╗██║    ██   ██║██╔══██║╚██╗ ██╔╝██╔══██║██╔══╝  ██║     ██║ ██╔██╗ \n" +
				"██║  ██║███████╗███████╗███████╗╚██████╔╝    ██║██║ ╚████║    ╚█████╔╝██║  ██║ ╚████╔╝ ██║  ██║██║     ███████╗██║██╔╝ ██╗\n" +
				"╚═╝  ╚═╝╚══════╝╚══════╝╚══════╝ ╚═════╝     ╚═╝╚═╝  ╚═══╝     ╚════╝ ╚═╝  ╚═╝  ╚═══╝  ╚═╝  ╚═╝╚═╝     ╚══════╝╚═╝╚═╝  ╚═╝\n" +
				"                                                                                                                          \n");
	}

}
