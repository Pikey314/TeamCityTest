package Build_NUnit.buildTypes

import jetbrains.buildServer.configs.kotlin.v2018_1.*
import jetbrains.buildServer.configs.kotlin.v2018_1.buildSteps.dotnetBuild
import jetbrains.buildServer.configs.kotlin.v2018_1.buildSteps.dotnetRestore
import jetbrains.buildServer.configs.kotlin.v2018_1.triggers.vcs

object Build_NUnit_RunTestsDefaultLoggingLevel : BuildType({
    name = "run tests, default logging level"

    vcs {
        root(_Self.vcsRoots.HttpsGithubComBurnashevaNunitCore2gitRefsHeadsMaster)
    }

    steps {
        dotnetRestore {
            projects = "PrimeService/PrimeService.csproj"
        }
        dotnetBuild {
            projects = "PrimeService/PrimeService.csproj"
        }
        dotnetRestore {
            projects = "PrimeServiceTests/PrimeServiceTests.csproj"
        }
        dotnetBuild {
            projects = "PrimeServiceTests/PrimeServiceTests.csproj"
            args = "/t:vstest"
            param("dotNetCoverage.dotCover.home.path", "%teamcity.tool.JetBrains.dotCover.CommandLineTools.DEFAULT%")
        }
    }

    triggers {
        vcs {
        }
    }
})