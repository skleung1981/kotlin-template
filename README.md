# Kotlin Project Template using Gradle

A Kotlin Project Template using Gradle and Kotlin DSL. 

This project template is assumed to be used internally on my local CI/CD environment. It can be done by
- Using internal Nexus as maven repository
  - store build files (JAR, WAR)
  - store docker images 
- Using internal Jenkins to build as normal CI practices
- Using internal Forgejo as source version control 

## How to use this project template
1. Create a new repo using this template or clone it
2. Rename the JenkinsTemplate to Jenkinsfile
3. Update the image name in the Jenkinsfile (Line 53)
4. Update the rootProject.name in the settings.gradle.kts
5. Update the group and version in build.gradle.kts
6. (Optional) Create a Dockerfile if it is necessary
7. Rewrite this README.md
8. Update the CHANGELOD.md

## How to build 

### Local Build

1. Create the gradle.properties in .gradle folder under user home directory.
   - Paste the credential that access the nexus to the file
   - Make sure file permission
   ```
    nexusUsername=<username>
    nexusPassword=<password>
   ```
2. Create a directory and file (init.d/nexus.init.grade.kts) in .gradle folder under user home directory
   - Paste the following content
   - Change nexus address.
   ```
   val nexusPublic = "https://saturn:30088/cicd/nexus/repository/maven-public/"
   {
    beforeSettings {
      pluginManagement {
        repositories {
          maven {
            name = "nexus"
            url = uri(nexusPublic)
            credentials(PasswordCredentials::class)
        }
      }
    }
    dependencyResolutionManagement {
      repositories {
        maven {
          name = "nexus"
          url = uri(nexusPublic)
          credentials(PasswordCredentials::class)
        }
      }
    }
   } 
   ```
3. Run the following command to build
    ```
    ./gradew build
    ```

### Build using Jenkins
Besides rename the Jenkins template file and update the Jenkinsfile, some of additional setting must be done

- Create a configuration file in Jenkins using Jenkins Plugin (Config File Provider Plugin) in order to access the internal nexus

## Rule for versioning
- Tag format use v as prefix, such as v1.0.0
- Update the CHANGELOG.md