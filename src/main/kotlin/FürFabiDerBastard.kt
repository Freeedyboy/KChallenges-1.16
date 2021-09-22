import org.bukkit.entity.Entity
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.event.entity.EntityDamageEvent
import org.bukkit.util.Vector


class FürFabiDerBastard: Listener {

    @EventHandler
    fun onEntityAttack(event: EntityDamageByEntityEvent){

        if(isRunning()){
            val p: Entity = event.getEntity()

            p.setVelocity(event.damager.location.direction.setY(0).normalize().multiply(20))
        }
    }

    @EventHandler
    fun onEntityDamage(event: EntityDamageEvent){
        if(event.entity is Player){
            if(event.cause == EntityDamageEvent.DamageCause.FALL){
                event.entity.velocity = Vector(0.0, event.damage * 5.0, 0.0)
            }
        }
    }
}