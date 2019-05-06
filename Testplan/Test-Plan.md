

# Test Plan

## 1. Introduction

### 1.1 Purpose
The purpose of the Iteration Test Plan is to gather all of the information necessary to plan and control the test effort for a given iteration. It describes the approach to testing the software, and is the top-level plan generated and used by managers to direct the test effort.
This Test Plan for DigiWill supports the following objectives:
* Identifies the items that should be targeted by the tests.
* Identifies the motivation for and ideas behind the test areas to be covered.
* Outlines the testing approach that will be used.
* Identifies the required resources and provides an estimate of the test efforts.
* Lists the deliverable elements of the test project.

### 1.2 Scope
This document describes the used tests, as they are unittests and functionality testing.
### 1.3 Intended Audience
This document is meant for internal use primarily.
### 1.4 Document Terminology and Acronyms
* SRS - Software Requirements Specification
* SOL - Sign of life
* n/a - not applicable
* tbd - to be determined

### 1.5 References
* [Github](https://github.com/DigiWill-dhbw/DigiWill)
* [Blog](https://digiwill71076886.wordpress.com/)
* [Use Case Diagram](https://github.com/DigiWill-dhbw/Documentation/blob/master/SRS/UC_Diagram.png)
* [SRS](https://github.com/DigiWill-dhbw/Documentation/blob/master/SRS/SRS.md)
* [SAD](https://github.com/DigiWill-dhbw/Documentation/blob/master/SAD/SAD.md)

## 2. Evaluation Mission and Test Motivation

### 2.1 Background
By testing our project, we intend to provide stability and safety for our project when the source code changes. The integration of the testing in our development process, allows us to be sure that only working versions of our software are deployed. The application is therefore always stable.

### 2.2 Evaluation Mission
Our motivation in implementing tests came with beginning the software development, as changes from different contributers interfered and created errors. So we created tests to be informed of errors created in other parts of the project.
### 2.3 Test Motivators
Our testing is motivated by:

* technical risks
* quality risks
* find as most bugs as possible

## 3. Target Test Items
The listing below identifies those test items (software, hardware, and supporting product elements) that have been identified as targets for testing. This list represents what items will be tested.

Items for Testing:

* Backend
* Web application
* Mobile application

## 4. Outline of Planned Tests

### 4.1 Outline of Test Inclusions
Backend:
* Unit testing

Web application:
* UI testing

Mobile application:
* UI testing

### 4.2 Outline of Other Candidates for Potential Inclusion
Stress testing the application might be potential test cases but these are not in scope of our testing process yet.
### 4.3 Outline of Test Exclusions
N / A
## 5. Test Approach

### 5.1 Testing Techniques and Types

#### 5.1.1 Unit Testing
TODO

#### 5.1.2 User Interface Testing
TODO

#### 5.1.3 Stress Testing
TODO

## 6. Entry and Exit Criteria

### 6.1 Test Plan

#### 6.1.1 Test Plan Entry Criteria
After a successfull build the testprocess will be executed.

#### 6.1.2 Test Plan Exit Criteria
All tests pass without throwing an error.

## 7. Deliverables

### 7.1 Test Evaluation Summaries
We are using our self hosted <a href="https://jenkins.robinkuck.de/blue/organizations/jenkins/DigiWill/activity/">Jenkins server</a> to build, run tests and deploy our application automatically.

The picture below shows our recent Jenkins builds:

<img src="jenkins_activity.png"
     alt="Recent Jenkins builds"/>

Our Jenkins execution steps called pipeline are designed as follows:

<img src="jenkins_pipeline.png"
     alt="Jenkins pipeline"/>

### 7.2 Reporting on Test Coverage
After building and testing the master branch the resulting coverage is uploaded to [codacy.com](https://app.codacy.com/project/DigiWill/DigiWill/dashboard).

[![Codacy Badge](https://api.codacy.com/project/badge/Coverage/43518fe6b862492eb945b71f57d521ce)](https://www.codacy.com/app/DigiWill/DigiWill?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=DigiWill-dhbw/DigiWill&amp;utm_campaign=Badge_Coverage)

### 7.3 Perceived Quality Reports
For quality reports we use [codacy.com](https://app.codacy.com/project/DigiWill/DigiWill/dashboard) and [lgtm.com](https://lgtm.com/projects/g/DigiWill-dhbw/DigiWill). They show errors in the code as well as other metrics. For every pull request the above tools are creating reports and bad quality reports must be fixed before merging is allowed.

The quality report status is showed by the following badges: 

| Badge | Description |
|---|---|
| [![Codacy Badge](https://api.codacy.com/project/badge/Grade/5c06f80d7ed24ae784247cb31d8c6a58)](https://app.codacy.com/app/DigiWill/DigiWill?utm_source=github.com&utm_medium=referral&utm_content=DigiWill-dhbw/DigiWill&utm_campaign=Badge_Grade_Dashboard) | Codacy quality report |
| [![Language grade: Java](https://img.shields.io/lgtm/grade/java/g/DigiWill-dhbw/DigiWill.svg?logo=lgtm&logoWidth=18)](https://lgtm.com/projects/g/DigiWill-dhbw/DigiWill/context:java) | Lgtm Java language grade |
| [![Language grade: JavaScript](https://img.shields.io/lgtm/grade/javascript/g/DigiWill-dhbw/DigiWill.svg?logo=lgtm&logoWidth=18)](https://lgtm.com/projects/g/DigiWill-dhbw/DigiWill/context:javascript) | Lgtm JavaScript language grade |

### 7.4 Incident Logs and Change Requests
The above mentioned tools are integrated in our pull requests on Github. If a Jenkins build or quality checks fail the merge function is blocked. In addition at least one developer needs to review the pull request.

<img src="pull_request.png"
     alt="Pull Request Image"/>

## 8. Testing Workflow
Every developer can run tests inside the IDE manually. Whenever new commits are pushed or a new pull request is created all tests will be executed automatically. 

## 9. Environmental Needs

### 9.1 Base System Hardware
TODO

### 9.2 Base Software Elements in the Test Environment
TODO

### 9.3 Productivity and Support Tools
TODO

### 9.4 Test Environment Configurations
TODO

## 10. Responsibilities, Staffing, and Training Needs

### 10.1 People and Roles
| Role | Minimum Resources Recommended | Specific Responsibilities |
|------|-------------------------------|---------------------------|
| Test Manager | 1 | Ensures that tests are complete and designed correctly |
| Test Designer | 1 | Defines how and which tests should be implmemented | 
| Test System Administrator | 1 | Ensures test environment and assets are maintained | 
| Tester | 3 | Implement tests for new functionality |

### 10.2 Staffing and Training Needs
n/a

## 11. Iteration Milestones
The goal is to keep over **70%** code coverage.

## 12. Risks, Dependencies, Assumptions, and Constraints
| Risk | Mitigation Strategy | Contingency |
|------|---------------------|-------------|
| UI Test Runner is not working | Search for different UI Test libraries | Replace test configuration |