# Software Configuration Management Plan (SCMP)
## Introduction 
We are creating a Flight Management system, compliant with DO-178C and with accompanying simulation software to perform testing.

## Configuration Management Objectives:
- Ensuring the integrity, traceability, and consistency of software and related documentation throughout the development lifecycle

## Configuration Identification:
- Use the default Branch names assigned by Gitlab
- Use class names such that they avoid potential conflict with other classes (from packages or other team members)
- Use meaningful Commit Messages

## Configuration Control:
- Use Branches and Merge Requests when making changes to the code
- Each member should commit to their own branch, relating to the current task they are working on.
- Use Issues to track current problems in the project, assigning the issue and notifying the member that is - responsible for that section in the code base whilst maintaining traceability. Issues can refer to other issue using “#” to ensure traceability.
- Merge Requests and new Branches can be created from the issues to continue the traceability.
- A new branch should be created along with a new Merge Request.

### Merge Requests
- Merge requests should be submitted with the following information:
    - The high-level or low-level requirement which the Merge Request solves
    - A description of the changes made. 
    - The testing results of the updated code. 
    - Any potential issues that may arise from the changes.
    - This information should be added to the comments of the Merge Request
- Merge requests should be verified by at least one other member of the group, who will review the code, and ensure no bugs exist.

## Configuration Audits and Reviews
- At regular weekly intervals, the entire code will be tested both on the personal laptops as well as the ECS University Systems to ensure compatibility
- The testing reviews will be presented to the team