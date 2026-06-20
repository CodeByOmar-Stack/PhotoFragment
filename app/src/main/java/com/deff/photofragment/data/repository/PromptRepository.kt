package com.deff.photofragment.data.repository

import com.deff.photofragment.data.model.FragmentPrompt

object PromptRepository {
    val prompts = listOf(
        FragmentPrompt("1", "Una estación vacía de noche", "https://images.unsplash.com/photo-1515165597738-98445f18274d?q=80&w=1000"),
        FragmentPrompt("2", "Una carta sin abrir sobre la mesa", "https://images.unsplash.com/photo-1516410529446-2c777cb7366d?q=80&w=1000"),
        FragmentPrompt("3", "Luces encendidas en un edificio lejano", "https://images.unsplash.com/photo-1449824913935-59a10b8d2000?q=80&w=1000"),
        FragmentPrompt("4", "El rastro de un avión cruzando el cielo azul", "https://images.unsplash.com/photo-1506744038136-46273834b3fb?q=80&w=1000"),
        FragmentPrompt("5", "Una taza de café olvidada en el porche", "https://images.unsplash.com/photo-1495474472287-4d71bcdd2085?q=80&w=1000")
    )
}
