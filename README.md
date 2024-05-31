# SWEN326 Project - Group/Team 8

## Avionics System

### Requirements

1. Manage flight plans 
2. Make corrections to the aircraft’s control systems and control surfaces
3. Monitor systems
4. Provide a UI to the pilots
---

### Team Members

1. Ricky Fong
2. Louis Isbister
3. Samuel (Sam) Richardson
4. Aidan (Lupi) Robins.
5. Risheet Surya Peri

---

### Run the Project

To develop the system we used **Eclipse**.

P.S. If the `SWEN326-Project-FCS` package comes with an error in eclipse:
1. Right Click and click on `Build Path` then `Configure Build Path`
2. Double click on the file underneath `Class Path`
3. Navigate to the root directory of this repository and select the `.jar` file
4. Click on `Apply and Close`

Run these Java Files in the same following order:

1. `/Phases/Implementation_Phase/swen326/SWEN326-Project-FCS/src/fcs/FlightController.java`
2. `/Phases/Implementation_Phase/swen326/SWEN326-Project-Simulator/src/Simulator.java`
3. `/Phases/Implementation_Phase/swen326/SWEN326-Project-Tester/src/test/Tester.java `    as JUnit Test
4. `/Phases/Implementation_Phase/swen326/SWEN326-Project-UI/src/ui/UI.java`

---
### Project Hierarchy

We have divided the project into directories correlating to each of the phases as mentioned in **DO-178C**

As of now:

* Project Root
    * Phases
        * Planning Phase
            * `Software Development Plan (SDP)`
            * `Software Configuration Management Plan (SCMP)`
            * `Software Quality Assurance Plan (SQAP)`
            * `Software Verification Plan (SVP)`
            * `Pilot UI Plan`
        * Requirements Phase
            * `Requirements Traceability Matrix`
        * Design Phase
            * `UML Diagram`
            * `Fault Tree Analysis`
        * Implementation Phase
            * `CODE BASE with Tests`
        * Integration Phase
        * Verification Phase
    * Meetings
        * ` meetings summaries `
    * README.md
    * Group_Agreement.md
---