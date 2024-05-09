# Software Development Plan
## Introduction 
We are creating a Flight Management system, compliant with DO-178C and with accompanying simulation software to perform testing.

## Project Organisation
### Team Member Responsibilities
- Before merging branches, at least one other member should review the code
- Packages should be utilised to create layered architecture
- Each group member should contribute to at least one component of the project.
### Modules 
1. Main Aircraft Control Program
1. Simulation Software
1. Testing Framework
1. UI
We are using external libraries to create the UI: these libraries may not be DO-178C complient - thus there must be a separation between the UI module and other modules.

## Development
### Environments
- We will use the latest Eclipse version
- To manage Git, we will use GitHub Desktop
### Languages
- The software will be written in Java 17 (LTS build), using David Pearce's Power of Ten coding Standard and Guidelines
### Tools
- SpotBugs
- CheckStyle, using Google's Java Style Guide with the following changes
  - Indents are 4 ASCII horizontal space characters (0x20)
- JUnit
### GitLab
- Configure .gitignore so development artefacts are not commit
- Use branches 
- Meaningful commit messages that can be used to ensure tracability between committed code and requirements.
- Only commit tested code

## Schedule
Twice-weekly meetings will take place on Monday 1200-1400 and Thursday 1400-1600.
1. Planning and Requirements (1-2w)
1. Design and Implementation (1-2w)
1. Verification and Quality Assurance (1-2w)
Total project duration: 5w