# My Personal Project

## Task Reminder Program

SDK: corretto-1.8


**Project Proposal:**
- The behavior of this application is to add tasks with specific due time and description for your future 
plan. This program will remind you when your tasks are due soon. This program is for the people who want to make
goals for their future, or the people who easily forget things. The reason for making this program is that I am 
the person who forget things easily. A task reminder is the tool for me to keep up with my plans, and it also helps me 
plan my school live.  


## User stories:
- As a user, I want to be able to add a task to my reminders
- As a user, I want to be able to check the number of remaining tasks on my reminders
- As a user, I want to be able to mark a specific task as completed on my reminders
- As a user, I want to be able to delete a specific task from my reminders
- As a user, I want to be able to save my to-do list to file
- As a user, I want to be able to view my to-do tasks in file
- As a user, I want to be able to view the detailed information of a task
- As a user, I want to be able to be able to load my to-do list from file 
- As a user, I want to be able to change the due time of a task on my reminders (not implemented yet)
- As a user, I want to be able to change the description of a task on my reminders (not implemented yet)
- As a user, I want to be able to change the name of a task on my reminders(not implemented yet)
## Phase 4: Task 2:
- Test and design a class in your model package that is robust. The markTaskCompleted method in 
TaskList class.
## Phase 4: Task 3:
- Each MainFrame, AddTask, and ViewTasks object is associated with only one TaskList object, and one 
TaskList object is associated with 0 to many Task object.
Each ViewTasks is associated with one JsonWriter object and each MainFrame object is associated
with one JsonReader object. There are still many duplications of code and too many lines of code
in the constructor. I would extract them into private helper methods.