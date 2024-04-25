package com.mat.api;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;


@SpringBootApplication
@RequiredArgsConstructor
public class MatSpringApplication extends SpringBootServletInitializer {

    public static void main(final String[] args) {
        ApplicationContext context = SpringApplication.run(MatSpringApplication.class, args);
        //initUsers(context.getBean(UserService.class));
    }
/*

    private final UserService userService;
    private final ProjectService projectService;
    private final ConfigurationService configurationService;
    private final ConfigurationReferenceService configurationReferenceService;


    @PostConstruct
    public void initUsers() {
        List<RegisterRequest> usersToAdd = new ArrayList<>();

        // Premier utilisateur
        */
/*RegisterRequest user1 = RegisterRequest.builder()
                .firstName("Rahma")
                .lastName("Rahma")
                .username("rahma123")
                .email("rahma-7123@gmail.com")
                .password("123456789")
                .profileIds(new ArrayList<>(List.of(1L)))
                .build();
        usersToAdd.add(user1);

        // Deuxième utilisateur
        RegisterRequest user2 = RegisterRequest.builder()
                .firstName("John")
                .lastName("Doe")
                .username("johndoe")
                .email("johndoe8@gmail.com")
                .password("password123")
                .profileIds(new ArrayList<>(List.of(1L)))
                .build();
        usersToAdd.add(user2);*//*


        // Premier utilisateur
        RegisterRequest user3 = RegisterRequest.builder()
                .firstName("kamal")
                .lastName("kamal")
                .username("kamal123")
                .email("kamal-7123@gmail.com")
                .password("123456789")
                .profileIds(new ArrayList<>(List.of(1L)))
                .build();
        usersToAdd.add(user3);

        // Ajouter les utilisateurs à la base de données
        usersToAdd.forEach(userService::addUser);
    }

    @PostConstruct
    public void initProjects() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        List<ProjectDto> projects = new ArrayList<>();
        ProjectDto projet1 = ProjectDto.builder()
                .name("Nouveau projet")
                .description("Ceci est une description de mon nouveau projet")
                .startDate(LocalDate.parse("2024-08-12", format))
                .endDate(LocalDate.parse("2024-11-12", format))
                .status("ACTIVE")
                .userIds(new ArrayList<>(List.of(1L)))
                .build();
        projects.add(projet1);
        projects.forEach(projectService::saveProject);

    }

    @PostConstruct
    public void initConfigurations() {
        List<ConfigurationDto> configurations = new ArrayList<>();
        ConfigurationDto configuration1 = ConfigurationDto.builder()
                .name("Nouveau projet")
                .content("Ceci est une description de mon nouveau projet")
                .configurationReferenceId(123L)
                .projectId(1L)
                .build();
        configurations.add(configuration1);
        configurations.forEach(configurationService::saveConfiguration);

    }

    @PostConstruct
    public void initConfigurationReference() {
        List<ConfigurationReferenceDTO> configurationReferences = new ArrayList<>();
        ConfigurationReferenceDTO configurationReferenceDTO1 = ConfigurationReferenceDTO.builder()
                .name("Nouveau projet")
                .content("Ceci est une description de mon nouveau projet")
                .build();
        configurationReferences.add(configurationReferenceDTO1);
        configurationReferences.forEach(configurationReferenceService::saveConfigurationReference);

    }

*/

}





