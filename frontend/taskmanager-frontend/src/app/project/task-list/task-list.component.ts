import { Component } from '@angular/core';
import { Task } from '../../task.model';

@Component({
  selector: 'app-task-list',
  standalone: true,
  imports: [],
  templateUrl: './task-list.component.html',
  styleUrl: './task-list.component.css'
})
export class TaskListComponent {
 tasks: Task[] = [{

  id: 1,
name: "Develop Front end",
description : "this is the description",
completed : true,
dueDate: new Date('06-03-2025'),
project : 1

},
{

  id: 1,
name: "Design Wireframe",
description : "this is the description",
completed : false,
dueDate: new Date('06-25-2025'),
project : 1

},
{

  id: 1,
name: "Design Wireframe",
description : "this is the description",
completed : false,
dueDate: new Date('06-25-2025'),
project : 1

},{

  id: 1,
name: "Design Wireframe",
description : "this is the description",
completed : false,
dueDate: new Date('06-25-2025'),
project : 1

}

]






}
