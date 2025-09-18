# User Guide: Getting Start With David!

//screenshot

## Introduction
David is a chatbot that keeps track of your tasks. It's:
1. executed in the conversational platform
2. able to perform in both GUI and command window
3. able to handle tasks like **"to do"**, **"deadline"**, **"event"**
4. able to parse time of deadline and event tasks in a specified format
5. durable in storing the tasks after the execution

## How to read the storage file?
The storage file is located in ***root/data/David.txt***.
It contains the input file representation of each task in the list.

For task type "To Do", 
the input format should be `T | 0/1 | [description]`. 
"T" denotes the type "To Do", 
"0" means the task is not done, 
"1" means the task is marked as done. 
The last field is task description.
For example, `T | 1 | read book`.

For task type "Deadline",
the input format should be `D | 0/1 | [description] | [end time]`. 
"D" denotes the type "Deadline",
"0" or "1" denotes whether the task is done (similar to above).
The third field is task description,
and the last field is the ending time.
For example, `D | 0 | return book | June 6th`.

For task type "Event",
the input format should be `E | 0/1 | [description] | [start time] - [end time]`. 
"E" denotes the type "Event".
"0/1" and description are similar to above.
For the last field, starting time is on the left hand side of "-",
and ending time is on the right hand side.
For example, `E | 0 | project meeting | Aug 6th 2pm - 4pm
`

## How to read the output of David?
David will give a list of tasks when it answers list commands,
and will show the string representation of the task when 
it answers add or mark commands.

For task type "To Do",
the output format should be `[T][ ] description`. 
The second box will be `[X]` if it is done.
For example, `[T][X] read book`.

For task type "Deadline",
the output format should be `[D][ ] description (by: end time)`.
Similar for the second box.
For example, `[D][ ] return book (by: Dec 2 2019, 6:00PM)
`.

For task type "Event",
the output format should be `[E][ ] description (from: start time to: end time)`. 
Similar for the second box.
For example, `[E][ ] team project (from: Aug 17 2025 to: Aug 25 2025, 6:00PM)
`.


## How to use the commands?
### 1. Add Command
### 1.1 `todo [description]`
Adds a "ToDo" task to the list. 
A ToDo task has no time specifier but only task description.

Example of usage: 

- `todo review lectures`

Expected outcome:
```
    ____________________________________________________________________________________________________
     Got it. I've added this task:
      [T][ ] review lectures
     Now you have 5 tasks in the list.
    ____________________________________________________________________________________________________
```
A new line will be added to the storage file: 
```T | 0 | review lectures```

### 1.2 `deadline [description] /by [end time]`
#### end time format to be parsed: yyyy-MM-dd (HHmm)
("yyyy" denotes year, "MM" denotes month, "dd" denotes date.
Time specifier "HHmm" is optional. "HH" denotes hour, "mm" denotes minute.)

The time will be parsed into "MMM d yyyy, h:mma". "a" denotes AM/PM.

Adds a "Deadline" task to the list.
Besides description, a Deadline Task also has ending time.

Example 1 of usage: 

- `deadline return book /by Sunday` 
(End time not parsed by David)

Expected outcome:
```
    ____________________________________________________________________________________________________
     Got it. I've added this task:
      [D][ ] return book (by: Sunday)
     Now you have 5 tasks in the list.
    ____________________________________________________________________________________________________
```

A new line will be added to the storage file: 
```D | 0 | return book | Sunday```

Example 2 of usage:

- `deadline return book /by 2025-08-17`
(Parse end time into ***Aug 17 2025*** without specification of hours and minutes)

Expected outcome:
```
    ____________________________________________________________________________________________________
     Got it. I've added this task:
      [D][ ] return book (by: Aug 17 2025)
     Now you have 5 tasks in the list.
    ____________________________________________________________________________________________________
```
A new line will be added to the storage file: 
```D | 0 | return book | Aug 17 2025```

Example 3 of usage:

- `deadline return book /by 2025-08-17 1800`
(Parse end time into ***Aug 17 2025, 6:00PM*** 
with specification of hours and minutes)

Expected outcome:
```
    ____________________________________________________________________________________________________
     Got it. I've added this task:
      [D][ ] return book (by: Aug 17 2025, 6:00PM)
     Now you have 5 tasks in the list.
    ____________________________________________________________________________________________________
```
A new line will be added to the storage file: 
```D | 0 | return book | Aug 17 2025, 6:00PM```

### 1.3 `event [description] /from [start time] /to [end time]`
#### start & end time format to be parsed: yyyy-MM-dd (HHmm)
Adds an "Event" task to the list.
An Event Task has starting time and ending time.

Example 1 of usage:

- `event project meeting /from Mon 2pm /to 4pm`
(Time not parsed by David)

Expected outcome:
```
    ____________________________________________________________________________________________________
     Got it. I've added this task:
      [E][ ] project meeting (from: Mon 2pm to: 4pm)
     Now you have 5 tasks in the list.
    ____________________________________________________________________________________________________
```
A new line will be added to the storage file:
```E | 0 | project meeting | Mon 2pm - 4pm```

Example 2 of usage:

- `event team project /from 2025-08-17 /to 2025-08-25 1800`

(Time parsed by David)

Expected outcome:
```
    ____________________________________________________________________________________________________
     Got it. I've added this task:
      [E][ ] team project (from: Aug 17 2025 to: Aug 25 2025, 6:00PM)
     Now you have 5 tasks in the list.
    ____________________________________________________________________________________________________
```
A new line will be added to the storage file:
```E | 0 | team project | Aug 17 2025 - Aug 25 2025, 6:00PM```

**The first word of the add command must be one of "todo",
"deadline", or "event".** Otherwise, it will print the error message:
```Error: the type (first word) you entered is invalid.```

### 2. Mark Command
### 2.1 `mark index`
Mark the task corresponding to the index (1-based) in the list as done.
First, the index should be a valid integer, 
otherwise the error message will be printed:
```Error: the value you entered after mark is not a valid integer.```

Second, index should be within the bound of list, 
otherwise the error message will be printed:
`Error: the value you entered after mark is out of bound.`

Example of usage:
- `mark 2`

Expected outcome:
```
    ____________________________________________________________________________________________________
     Nice! I've mark this task as done:
      [D][X] return book (by: June 6th)
    ____________________________________________________________________________________________________
```
For the second line of the input file,
the second field will be written to "1".

### 2.2 `unmark index`
Mark the task corresponding to the index (1-based) in the list as undone.
Usage is similar to `mark index`. For the corresponding line of the input file,
the second field will be written to "0".

Example of usage:
- `unmark 2`

Expected outcome:
```
    ____________________________________________________________________________________________________
     OK, I've marked this task as not done yet:
      [D][ ] return book (by: June 6th)
    ____________________________________________________________________________________________________
```

### 3. List Command
### `list`
Prints all the task from the list stored by David.

Expected outcome:
```
    ____________________________________________________________________________________________________
     Here are the tasks in your list:
     1. [T][X] read book
     2. [D][ ] return book (by: June 6th)
     3. [E][ ] project meeting (from: Aug 6th 2pm to: 4pm)
     4. [T][X] join sports club
    ____________________________________________________________________________________________________
```

### 4. Delete Command
### `delete index`
Delete the task corresponding to the index (1-based) in the list.
Usage similar to `mark index`.

Example of usage:
- `delete 2`

Expected outcome:
```
    ____________________________________________________________________________________________________
     Noted, I've removed this task:
      [D][ ] return book (by: June 6th)
     Now you have 3 tasks in the list.
    ____________________________________________________________________________________________________
```
The second line of the input file will be deleted.

### 5. Find Command
### `find keyword`
Prints the list of tasks whose task description matches the keyword.
If the keyword is a substring of the description, it will also match.

Example of usage:
- `find book`

Expected outcome:
```
    ____________________________________________________________________________________________________
     Here are the matching tasks in your list:
     1. [T][X] read book
     2. [D][ ] return book (by: June 6th)
    ____________________________________________________________________________________________________
```

### 6. Undo Command
### `undo`
Undoes the command that changes the task list, one at the time.
Commands will be undone are: add, delete, mark, and unmark command.

Expected outcome 1: (if there are commands to be undone)

```
    ____________________________________________________________________________________________________
     Okay, I've undone the last action.
    ____________________________________________________________________________________________________
```
The input file will be reverted to the last change at the same time.

Expected outcome 2: (if no command is left to be undone)

```
    ____________________________________________________________________________________________________
     There is nothing to undo!
    ____________________________________________________________________________________________________
```

### 7. Exit Command
### `bye`
Terminates the execution and saves the latest change of the list.

Expected outcome:
```
    ____________________________________________________________________________________________________
     Bye. Hope to see you again soon!
    ____________________________________________________________________________________________________
```