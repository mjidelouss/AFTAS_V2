import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LevelService {
  private apiUrl = 'http://127.0.0.1:8080/api/level';

  constructor(private http: HttpClient) {}

  getLevels(): Observable<any> {
    return this.http.get<any>(this.apiUrl);
  }

  addLevel(level: any): Observable<any> {
    return this.http.post(this.apiUrl, level);
  }

  deleteLevel(id: number): Observable<any> {
    const url = `http://127.0.0.1:8080/api/level/${id}`;
    return this.http.delete(url);
  }
}
