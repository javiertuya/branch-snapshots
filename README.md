# Branch snapshots

This is a sample on how to publish branch snapshots to the GitHub Maven registry:
- A GitHub Actions workflow with a job `publish-java-snapshot` that publishes
  independent snapshots for each branch, in the form `<version number>-<branch name>-SNAPSHOT`.
- The project pom.xml that includes the necessary configuration to publish the snapshots to GitHub Packages and the releases to Maven Central

Detailed information on GitHub Packages can be found here:
https://docs.github.com/en/packages/working-with-a-github-packages-registry/working-with-the-apache-maven-registry.

To consume (install) the snapshots from a local development environment, Jenkins or GitHub Actions, follow the below instructions.

## Configure the pom.xml (common to all environments)

Declare the GitHub repository in the pom.xml, example:
```xml
  <repositories>
    <repository>
      <id>github</id>
      <url>https://maven.pkg.github.com/javiertuya/branch-snapshots</url>
      <snapshots>
        <enabled>true</enabled>
      </snapshots>
    </repository>
  </repositories>
```

and follow the below instructions for each environment

## Install from GitHub Actions

The workflow requires authentication with a token with scope `read:packages`.
This can be achieved by specifying the environment variable
`GITHUB_TOKEN` associated with the workflow repository:

```yaml
      - name: Build and test
        run: mvn test -U
        env:
          GITHUB_TOKEN: ${{ secrets.GITHUB_TOKEN }}
```

## Install from a local development environment

Authentication must be included in a `settings.xml` file in your `~/.m2` folder.
The `id` (`github` in the example) must match the `id` indicated in the pom.xml file.
The GitHub token must have the `read:packages` scope:

```xml
<settings xmlns="http://maven.apache.org/SETTINGS/1.0.0"
  xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0
                      http://maven.apache.org/xsd/settings-1.0.0.xsd">
  <servers>
    <server>
      <id>github</id>
      <username>USERNAME</username>
      <password>TOKEN</password>
    </server>
  </servers>
</settings>
```

## Install from Jenkins

Autentication is similar to that of a local environment, but you can safely save the token in Jenkins:
- Place the `settings.xml` file in the jenkins node (agent) `~/.m2` folder.
- For security reasons, instead of use a plain text token,
you should use an environment variable, for example, `${GITHUB_TOKEN}`
- Add a "secret text" credential to Jenkins with the token ant take note of the `credential_id`
- Then, use the Jenkins Credentials Binding Plugin (https://www.jenkins.io/doc/pipeline/steps/credentials-binding/) 
  in your pipeline to inject the secret:
```groovy
    withCredentials([string(credentialsId: 'credential_id', variable: 'GITHUB_TOKEN')]) {
      sh 'mvn test -U'
    }
```
