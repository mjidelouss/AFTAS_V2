import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CompetitionService {

  private apiUrl = 'http://127.0.0.1:8080/api/competition';

  constructor(private http: HttpClient) {}

  getSimpleCompetitions() {
    return this.http.get<any>(this.apiUrl);
  }

  getCompetitions(pageIndex: number, pageSize: number): Observable<any> {
    const params = new HttpParams().set('page', pageIndex.toString()).set('size', pageSize.toString());
    return this.http.get<any>(this.apiUrl, { params });
  }

  getCompetitionsByStatus(status: string): Observable<any> {
    const url = `${this.apiUrl}/byStatus/${status}`;
    return this.http.get(url);
  }

  addCompetition(competition: any): Observable<any> {
    return this.http.post(this.apiUrl, competition);
  }

  updateCompetition(competition: any): Observable<any> {
    const updateApiUrl = `http://127.0.0.1:8080/api/competition/${competition.id}`;
    return this.http.put(updateApiUrl, competition);
  }

  deleteCompetition(id: number): Observable<any> {
    const url = `http://127.0.0.1:8080/api/competition/${id}`;
    return this.http.delete(url);
  }

}
