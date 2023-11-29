import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {FileSystemFileEntry} from "ngx-file-drop";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class VideoService {

  constructor(private httpClient: HttpClient) {
  }

  uploadFile(fileEntry: File) {
    const formData = new FormData()
    formData.append('file', fileEntry, fileEntry.name)
    //Http llamadas de los videos
    return this.httpClient.post("http://localhost:8080/api/videos/", formData);
  }
}
