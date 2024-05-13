# Software Verification Plan (SVP)
## Testing Standards
- Every method must have at least two assertions to abide by the power of ten.
- A merge cannot cause failures on test cases that were previously successful. That is, before a merge request can be approved it must be shown that it does not fail Regression Testing.
- Testing and simulation software will not develop at the same rate. As a result, new test cases may uncover errors in the simulation that were previously untested. In this scenario the developer of the updated test cases should assign an issue to the member whose code has been affected, detailing the errors that are occurring.

## MC/DC Coverage.
- Eclipse has built in methods for coverage testing. Get it as high as possible.

## Software Testing
- Testing the software using white and black box testing. 
    - Functional Testing  (J unit)
    - Non-Functional Testing
    - Regression Testing  (J unit)
    - Coverage Testing (J unit)


## Verification output
- The software’s output must meet the requirements during the testing. 

## Traceability
- Must be able to trace between requirements, design, implementation and testing. 
