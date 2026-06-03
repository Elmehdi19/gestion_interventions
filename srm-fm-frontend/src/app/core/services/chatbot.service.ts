import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ChatbotMessageRequest, ChatbotMessageResponse } from '../../shared/models/chatbot.models';

@Injectable({ providedIn: 'root' })
export class ChatbotService {
  private api = '/api/chatbot';

  constructor(private http: HttpClient) {}

  sendMessage(request: ChatbotMessageRequest): Observable<ChatbotMessageResponse> {
    return this.http.post<ChatbotMessageResponse>(`${this.api}/message`, request);
  }
}