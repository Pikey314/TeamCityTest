package Vstest_NUnit.buildTypes

import jetbrains.buildServer.configs.kotlin.v2018_1.*
import jetbrains.buildServer.configs.kotlin.v2018_1.buildSteps.DotnetVsTestStep
import jetbrains.buildServer.configs.kotlin.v2018_1.buildSteps.dotnetBuild
import jetbrains.buildServer.configs.kotlin.v2018_1.buildSteps.dotnetRestore
import jetbrains.buildServer.configs.kotlin.v2018_1.buildSteps.dotnetVsTest
import jetbrains.buildServer.configs.kotlin.v2018_1.triggers.vcs

object Vstest_NUnit_2RunTestsQuietLogging : BuildType({
    name = "run tests, quiet logging"

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
        dotnetVsTest {
            assemblies = "PrimeServiceTests/bin/Debug/netcoreapp2.0/PrimeServiceTests.dll"
            logging = DotnetVsTestStep.Verbosity.Quiet
            param("dotNetCoverage.dotCover.home.path", "%teamcity.tool.JetBrains.dotCover.CommandLineTools.DEFAULT%")
        }
    }

    triggers {
        vcs {
        }
    }
})