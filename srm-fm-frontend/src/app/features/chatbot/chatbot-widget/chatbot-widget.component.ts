import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ChatbotService } from '../../../core/services/chatbot.service';
import { ChatbotMessageResponse } from '../../../shared/models/chatbot.models';

interface Message {
  text: string;
  from: 'user' | 'bot';
  timestamp: Date;
}

@Component({
  selector: 'app-chatbot-widget',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './chatbot-widget.component.html',
  styleUrls: ['./chatbot-widget.component.scss']
})
export class ChatbotWidgetComponent {
  isOpen = false;
  messages: Message[] = [];
  newMessage = '';
  sessionId: number | undefined;

  constructor(private chatbotService: ChatbotService) {
    // Message d'accueil initial
    this.messages.push({
      text: 'Bonjour ! Je suis l\'assistant SRM-FM. Comment puis-je vous aider ?',
      from: 'bot',
      timestamp: new Date()
    });
  }

  toggleChat(): void {
    this.isOpen = !this.isOpen;
  }

  sendMessage(): void {
    const text = this.newMessage.trim();
    if (!text) return;

    // Ajouter le message utilisateur
    this.messages.push({ text, from: 'user', timestamp: new Date() });
    this.newMessage = '';

    // Appeler le service backend
    this.chatbotService.sendMessage({ message: text, sessionId: this.sessionId }).subscribe({
      next: (res: ChatbotMessageResponse) => {
        this.sessionId = res.sessionId;
        this.messages.push({ text: res.response, from: 'bot', timestamp: new Date() });
        // Gérer les redirections éventuelles
        if (res.intendToRedirect && res.redirectUrl) {
          window.open(res.redirectUrl, '_blank');
        }
      },
      error: () => {
        this.messages.push({
          text: 'Désolé, une erreur est survenue. Veuillez réessayer plus tard.',
          from: 'bot',
          timestamp: new Date()
        });
      }
    });
  }
}