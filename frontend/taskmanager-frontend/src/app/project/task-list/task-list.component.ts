import { Component, inject } from '@angular/core';
import { Task } from '../../task.model';
import { DatePipe } from '@angular/common';
import { TaskService } from '../../task.service';
import { TaskFormComponent } from '../task-form/task-form.component';

@Component({
  selector: 'app-task-list',
  standalone: true,
  imports: [DatePipe,TaskFormComponent],
  templateUrl: './task-list.component.html',
  styleUrl: './task-list.component.css'
})
export class TaskListComponent {

  tasks:Task[];

  showModal:boolean=false;

  private taskService= inject(TaskService);

  constructor(){
    this.tasks=this.taskService.getTasks();
  }

handleCheckBox(id:number){
  //console.log(id);
 const checkedIndex = this.tasks.findIndex((task)=>task.id===id);
 const updatedTask= this.tasks[checkedIndex];
 updatedTask.completed=!updatedTask.completed;
 this.tasks=this.taskService.updateTask(updatedTask);

}

deleteTask(id:number){
this.tasks=this.taskService.deleteTask(id);
}

toggleshowModal(){
  this.showModal=true;
}

}
