import type { User } from "../Interfaces/User.tsx";

interface ProfileProps {
    currentUser: User | null;
    tempUser: User | null;
    setPage: (page: 'menu' | 'login' | 'register' | 'cart' | 'order' | 'profile') => void;
    handleChange: (section: 'contact' | 'address' | 'main', field: string, value: string)=> void;
    handleSave: ()  => Promise<void>;
    editingField: string | null;
    setEditingField: (field: string | null) => void;
}
 interface EditableProfile {
    id: string;
    label?: string;
    value:string;
    isEditing: boolean;
    onEdit: () => void;
    onSave: () => void;
    onChange: (val: string) => void;
}
export const EditableRow = ({ label, value, isEditing, onEdit, onSave, onChange }: EditableProfile) => (
    <div className='individual'>
        {isEditing ? (
            <>
                <input
                    className="checkout-input-small"
                    value={value || ''}
                    onChange={(e) => onChange(e.target.value)}
                    autoFocus
                />
                <button className="save-btn" onClick={onSave}>
                    OK
                </button>
            </>
        ) : (
            <>
                {label && <strong>{label} </strong>}
                <span className='profile-info'>{value || 'Nepřidáno'}</span>
                <button className='edit-btn' onClick={onEdit}>
                    <img src='/public/edit.png' alt='edit'></img>
                </button>
            </>
        )}
    </div>
);

export default function Profile({ currentUser,tempUser, setPage,handleChange,handleSave,editingField,setEditingField }: ProfileProps) {
    if (!currentUser) {
        return (
            <div className="home-screen">
                <h2>Nejste přihlášen!</h2>
                <button onClick={() => setPage('login')}>Jít na přihlášení</button>
            </div>
        );
    }

    return (
        <div className="home-screen">
            <div className="profile-card">
                <h3>
                    Profil
                </h3>

                <div className="profile-info">
                    <div className='individual'>
                        <EditableRow
                            label={"Username:"}
                            id="username"
                            value={tempUser?.username|| ''}
                            isEditing={editingField === 'username'}
                            onEdit={() => setEditingField('username')}
                            onSave={() => handleSave()}
                            onChange={(val) => handleChange('main', 'username', val)}
                        />
                    </div>
                    <div className='individual'>
                        {editingField === 'name' ? (
                            <div className="name-row-editing" style={{ display: 'flex', gap: '8px' }}>
                                <input
                                    className="checkout-input-small firstName-input"
                                    placeholder="firstName"
                                    value={tempUser?.firstName || ''}
                                    onChange={(e) => handleChange('main', 'firstName', e.target.value)}
                                    autoFocus
                                />
                                <input
                                    className="checkout-input-small lastName-input"
                                    placeholder="lastName"
                                    style={{ width: '60px' }}
                                    value={tempUser?.lastName || ''}
                                    onChange={(e) => handleChange('main', 'lastName', e.target.value)}
                                />
                                <button className="save-btn" onClick={async () => {await handleSave();setEditingField(null)}}>OK</button>
                            </div>
                        ) : (
                            <>
                            <span className='profile-info'>
                               <strong>Jméno: </strong> {tempUser?.firstName} {tempUser?.lastName}
                            </span>
                                <button className='edit-btn' onClick={() => setEditingField('name')}>
                                    <img src='/public/edit.png' alt='edit' ></img>
                                </button>
                            </>
                        )}
                    </div>

                   <div className={'row-style'}></div>
                        <h4>
                            Kontakt
                        </h4>
                            <div className='individual'>
                                <EditableRow
                                    id="email"
                                    value={tempUser?.contact.email|| ''}
                                    isEditing={editingField === 'email'}
                                    onEdit={() => setEditingField('email')}
                                    onSave={() => handleSave()}
                                    onChange={(val) => handleChange('contact', 'email', val)}
                                />
                            </div>

                            <div className='individual'>
                                <EditableRow
                                    id="phoneNumber"
                                    value={tempUser?.contact.phoneNumber|| ''}
                                    isEditing={editingField === 'phoneNumber'}
                                    onEdit={() => setEditingField('phoneNumber')}
                                    onSave={() =>handleSave()}
                                    onChange={(val) => handleChange('contact', 'phoneNumber', val)}
                                />
                            </div>

                            <div className={'row-style'}></div>

                        <h4>
                            Adresa
                        </h4>
                            <div className='individual'>
                                {editingField === 'fullAddress' ? (
                                    <div className="address-row-editing" style={{ display: 'flex', gap: '8px' }}>
                                        <input
                                            className="checkout-input-small street-input"
                                            placeholder="Ulice"
                                            value={tempUser?.address.street || ''}
                                            onChange={(e) => handleChange('address', 'street', e.target.value)}
                                            autoFocus
                                        />
                                        <input
                                            className="checkout-input-small number-input"
                                            placeholder="č.p."
                                            style={{ width: '60px' }}
                                            value={tempUser?.address.houseNumber || ''}
                                            onChange={(e) => handleChange('address', 'houseNumber', e.target.value)}
                                        />
                                        <button className="save-btn" onClick={async () => {await handleSave();setEditingField(null)}}>OK</button>
                                    </div>
                                ) : (
                                    <>
                                        <span className='profile-info'>
                                            {tempUser?.address.street} {tempUser?.address.houseNumber}
                                        </span>
                                        <button className='edit-btn' onClick={() => setEditingField('fullAddress')}>
                                            <img src='/public/edit.png' alt='edit' />
                                        </button>
                                    </>
                                )}
                            </div>
                                <div className='individual'>
                                    <EditableRow
                                        id="city"
                                        value={tempUser?.address.city || ''}
                                        isEditing={editingField === 'city'}
                                        onEdit={() => setEditingField('city')}
                                        onSave={() => handleSave()}
                                        onChange={(val) => handleChange('address', 'city', val)}
                                    />
                                </div>

                    <button  className={'back-btn-profile'} onClick={() => setPage('menu')}>
                        Zpět na menu
                    </button>
                </div>
            </div>
        </div>
    );
}