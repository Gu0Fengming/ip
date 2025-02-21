# Whiost Chatbot User Guide


![Screenshot of an user using the chatbot.](/docs/Ui.png)

Whiost is a chatbot that helps you record your tasks on a smart memo pad. 


## Storing format
'[T][] taskname (time)'
the first bracket shows the type of the task: 'T' for 'todo', 'D' for 'deadline' and 'E' for 'event'. 
the second bracket shows the mark status of the task: 'x' for marked and none for unmarked. 
the third bracket shows the time of the task. (if applicable)


## Adding deadlines
Adding deadlines by input: deadline 'what' /by 'when'

Example: 
input 'deadline homework /by tonight'

expected output

```
Got it. I've added this task:
[D][] homework (by tonight)
Now you have x tasks in the list.
```


## Adding todos
Adding deadlines by input: todo 'what'

Example: 
input 'todo homework'

expected output

```
Got it. I've added this task:
[T][] homework
Now you have x tasks in the list.
```


## Adding events
Adding events by input: event 'what' /from 'when' /to 'when'

Example: 
input 'event party /from tonight /to tomorrow'

expected output

```
Got it. I've added this task:
[E][] party (from: tonight to: tomorrow)
Now you have x tasks in the list.
```


## Check current task list
Check current task list by input: list

Example: 
input 'list'

expected output (depends on what you have added in the past)

```
1.[D][] homework (by tonight)
2.[E][] party (from: tonight to: tomorrow)
```


## Marking/Unmarking tasks
Marking/Unmarking tasks by input: mark/unmark tasknumber

Example: 
current task list:
1.[D][] homework (by tonight)
2.[E][] party (from: tonight to: tomorrow)

input 'mark 1'

expected output

```
Nice! I've marked this task as done:
[D][X] homework (by tonight)
```


## Delete tasks
Delete tasks by input: delete tasknumber

Example: 
current task list:
1.[D][X] homework (by tonight)
2.[E][] party (from: tonight to: tomorrow)

input 'delete 1'

expected output

```
Noted. I've removed this task:
[D][X] homework (by tonight)
Now you have 1 tasks in the list. 
```


## Finding tasks
Find tasks by input: find keywords

Example: 
current task list:
1.[E][] party (from: tonight to: tomorrow)
2.[T][] homework
3.[D][]back to home (by 9pm)

input 'find home'

expected output

```
Here are the matching tasks in your list:
1.[T][] homework
2.[D][]back to home (by 9pm)
```