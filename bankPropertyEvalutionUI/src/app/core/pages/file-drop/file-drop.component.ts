import { Component, EventEmitter, Output } from '@angular/core';
import { AppDataSvc } from '../../../app.data.svc';

@Component({
  selector: 'app-file-drop',
  standalone: true,
  imports: [],
  templateUrl: './file-drop.component.html',
  styleUrl: './file-drop.component.scss'
})
export class FileDropComponent {
  user: string = '';
  @Output() fileDetails: EventEmitter<any> = new EventEmitter<any>();

  constructor(private appDataSvc: AppDataSvc) { }

  ngOnInit() {
    this.appDataSvc.getProfileObs().subscribe(profile => {
      if (profile) {
        this.user = JSON.parse(localStorage.getItem("user")!);
      }
    }, error => {
      console.error('Error fetching user profile:', error);
    });
  }

  highlight(event: DragEvent) {
    event.preventDefault();
    event.stopPropagation();
    const dropArea = event.target as HTMLElement;
    dropArea.classList.add('highlight');
  }

  unhighlight(event: DragEvent) {
    event.preventDefault();
    event.stopPropagation();
    const dropArea = event.target as HTMLElement;
    dropArea.classList.remove('highlight');
  }

  handleDrop(event: DragEvent) {
    event.preventDefault();
    event.stopPropagation();
    const dt = event.dataTransfer;
    const files = dt?.files;
    if (files != null) {
      this.handleFiles(files);
    }
  }

  handleFileInput(event: Event) {
    const files = (event.target as HTMLInputElement).files;
    if (files != null) {
      this.handleFiles(files);
    }
  }

  handleFiles(files: FileList) {
    for (let i = 0; i < files.length; i++) {
      const file = files[i];
      const fileDetail = {
        docType: file.type,
        fileName: this.getFileNameWithoutExtension(file.name),
        size: this.formatBytes(file.size),
        uploadedOn: new Date(),
        uploadedBy: this.user
      };
      this.fileDetails.emit(fileDetail);
    }
  }

  getFileNameWithoutExtension(filename: string): string {
    return filename.split('.').slice(0, -1).join('.');
  }

  // Function to format file size
  formatBytes(bytes: number, decimals: number = 2): string {
    if (bytes === 0) return '0 Bytes';
    const k = 1024;
    const dm = decimals < 0 ? 0 : decimals;
    const sizes = ['Bytes', 'KB', 'MB', 'GB', 'TB', 'PB', 'EB', 'ZB', 'YB'];
    const i = Math.floor(Math.log(bytes) / Math.log(k));
    return parseFloat((bytes / Math.pow(k, i)).toFixed(dm)) + ' ' + sizes[i];
  }
}