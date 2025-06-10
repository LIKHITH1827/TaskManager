import { Component } from '@angular/core';
import { Task } from '../../task.model';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-task-list',
  standalone: true,
  imports: [DatePipe],
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

  id: 2,
name: "Design Wireframe",
description : "this is the description",
completed : false,
dueDate: new Date('06-25-2025'),
project : 3

},
{

  id: 3,
name: "Design Wireframe",
description : "this is the description",
completed : false,
dueDate: new Date('06-25-2025'),
project : 2

},{

  id: 4,
name: "Design Wireframe",
description : "this is the description",
completed : false,
dueDate: new Date('06-25-2025'),
project : 1

}

]

handleCheckBox(id:number){
  //console.log(id);
 const checkedIndex = this.tasks.findIndex((task)=>task.id===id);
 this.tasks[checkedIndex].completed=!this.tasks[checkedIndex].completed;
}




}
