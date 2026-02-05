import org.moqui.context.ExecutionContext
import org.moqui.entity.EntityValue

ExecutionContext ec = context.ec

// 1. Verify that a Party record exists
EntityValue party = ec.entity.find("party.Party").condition("partyId", partyId).one()

if (!party) {
    ec.message.addError("Cannot create Person. Party with ID [${partyId}] does not exist.")
    return
}

// 2. Create the Person record using the implicit entity-auto service
Map createResult = ec.service.sync().name("create", "party.Person").parameters(context).call()

// 3. Return the exact response string requested
message = "Person ${firstName} ${lastName} created successfully!"
return [message: message]