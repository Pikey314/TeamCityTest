package Vstest_MSTest_DotCover.buildTypes

import jetbrains.buildServer.configs.kotlin.v2018_1.*
import jetbrains.buildServer.configs.kotlin.v2018_1.buildSteps.DotnetVsTestStep
import jetbrains.buildServer.configs.kotlin.v2018_1.buildSteps.dotnetVsTest
import jetbrains.buildServer.configs.kotlin.v2018_1.triggers.vcs

object Vstest_MSTest_DotCover_MinimalLogging : BuildType({
    name = "minimal logging"

    vcs {
        root(_Self.vcsRoots.HttpsGithubComBurnashevaMstestDotnet2gitRefsHeadsMaster)
    }

    steps {
        dotnetVsTest {
            assemblies = "PrimeService.Tests/bin/Debug/netcoreapp2.0/PrimeService.Tests.dll"
            logging = DotnetVsTestStep.Verbosity.Minimal
            coverage = dotcover {
                toolPath = "%teamcity.tool.JetBrains.dotCover.CommandLineTools.2017.2.20171006.131508%"
            }
        }
    }

    triggers {
        vcs {
        }
    }
})