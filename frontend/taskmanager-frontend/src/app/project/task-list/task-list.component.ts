import { Component, EventEmitter, inject, Output } from '@angular/core';
import { Task } from '../../task.model';
import { DatePipe, AsyncPipe } from '@angular/common';
import { TaskService } from '../../task.service';
import { TaskFormComponent } from '../task-form/task-form.component';
import { Observable } from 'rxjs';


const emptyTask={
  name:"",
  description:"",
  dueDate: new Date(),
 completed: false,
 project:0,
 id:4,
};


@Component({
  selector: 'app-task-list',
  standalone: true,
  imports: [DatePipe,AsyncPipe,TaskFormComponent],
  templateUrl: './task-list.component.html',
  styleUrl: './task-list.component.css'
})
export class TaskListComponent {

@Output() progressChanged= new EventEmitter<number>();


  tasks:Task[]=[];
   formType: "UPDATE" | "CREATE" = 'CREATE';
  showModal:boolean=false;
  selectedTask : Task = emptyTask;
  tasks$!:Observable<Task[]>


  private taskService= inject(TaskService);

  constructor(){
    this.updateTasks();
  }

private calculateAndEmitProgress(){
const completed= this.tasks.filter(task=>task.completed===true).length;
    const percentage=Math.round((completed)/(this.tasks.length)*100);
    this.progressChanged.emit(percentage);
}


updateTasks(){
    this.tasks$ = this.taskService.getTasks();
    this.tasks$.subscribe((tasks)=>{
  this.tasks=tasks;
   this.calculateAndEmitProgress();
    })
  }




handleCheckBox(task:Task){
  //console.log(id);

 const updatedTask= {...task, completed:!task.completed};

 this.taskService.updateTask(updatedTask).subscribe(()=>{
  task.completed = updatedTask.completed;
  this.calculateAndEmitProgress();
 });

}

updateTask(task:Task){
this.selectedTask= task;

this.formType='UPDATE';
this.showModal=true;




}



addNewTask(){
  this.selectedTask=emptyTask;
  this.formType='CREATE';
  this.showModal=true;
}

deleteTask(id:number){
this.taskService.deleteTask(id).subscribe(()=>{this.updateTasks();});

}

handleModalClose(type: 'SUBMIT' | 'CANCEL'){
if(type==='SUBMIT'){
   this.updateTasks();
}
this.showModal=false;
}


}
