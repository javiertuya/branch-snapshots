# Branch snapshots

This is a sample on how to publish a snapshot for each branch name.

The workflow includes a job `publish-java-snapshot` that deploys an artifact to GitHub maven repository
with independent snapshots for each branch, in the form `<version number>-<branch name>-SNAPSHOT`.
The pom.xml includes the necessary configuration to pubhlish the snapshots to GitHub and the releases to Maven Central

To consume (install) the snapshots from a local development environment, Jenkins or GitHub Actions,
declare the repository in the pom.xml:
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

The workflow requires authentication with a token with permission to read packages. The token that can be specified at the build step:
```yaml
      - name: Build and test
        run: mvn test -U
        env:
          GITHUB_TOKEN: ${{ secrets.GITHUB_TOKEN }}
```

## Install from a local local development environment

## Install from Jenkins


