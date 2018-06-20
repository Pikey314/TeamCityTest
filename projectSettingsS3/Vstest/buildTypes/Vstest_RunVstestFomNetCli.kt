package Vstest.buildTypes

import jetbrains.buildServer.configs.kotlin.v2018_1.*
import jetbrains.buildServer.configs.kotlin.v2018_1.buildSteps.DotnetVsTestStep
import jetbrains.buildServer.configs.kotlin.v2018_1.buildSteps.dotnetBuild
import jetbrains.buildServer.configs.kotlin.v2018_1.buildSteps.dotnetRestore
import jetbrains.buildServer.configs.kotlin.v2018_1.buildSteps.dotnetVsTest

object Vstest_RunVstestFomNetCli : BuildType({
    name = "run vstest fom .net cli"

    vcs {
        root(_Self.vcsRoots.HttpsGithubComBurnashevaMstestCoreAdapterGit)
    }

    steps {
        dotnetRestore {
            projects = "MSTestCore.sln"
            sources = "https://api.nuget.org/v3/index.json"
            param("dotNetCoverage.dotCover.home.path", "%teamcity.tool.JetBrains.dotCover.CommandLineTools.DEFAULT%")
        }
        dotnetBuild {
            projects = "MSTestCore.sln"
        }
        dotnetVsTest {
            assemblies = "PrimeService.Tests/bin/Debug/netcoreapp1.1/PrimeService.Tests.dll"
            filter = testCaseFilter {
                filter = "PrimeServices"
            }
            logging = DotnetVsTestStep.Verbosity.Diagnostic
            coverage = dotcover {
                toolPath = "%teamcity.tool.JetBrains.dotCover.CommandLineTools.DEFAULT%"
            }
            param("test.names", "ReturnFalse")
        }
    }
})